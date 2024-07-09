package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public int getManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<String> getManualLottoInputs(int manualCount) {
        List<String> manualLottoInputs = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualCount; i++) {
            manualLottoInputs.add(scanner.nextLine());
        }
        return manualLottoInputs;
    }

    public List<Integer> getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요. (예: 1, 2, 3, 4, 5, 6)");
        String input = scanner.nextLine();
        String[] inputs = input.split("[,\\s]+");
        List<Integer> winningNumbers = new ArrayList<>();
        for (String number : inputs) {
            winningNumbers.add(Integer.parseInt(number));
        }
        return winningNumbers;
    }

    public int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }
}
