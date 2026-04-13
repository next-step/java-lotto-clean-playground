package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        if (input.isBlank()) {
            throw new IllegalArgumentException("구입금액이 비어있습니다.");
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입금액은 숫자여야 합니다.");
        }
    }

    public String getWinnerNumbers() {
        System.out.println("\n지난 주 당첨번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public String getBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }

    public int getManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자여야 합니다.");
        }
    }

    public List<String> getManualNumbers(int count) {
        if (count > 0) {
            System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        }
        List<String> manualNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            manualNumbers.add(scanner.nextLine());
        }
        return manualNumbers;
    }
}