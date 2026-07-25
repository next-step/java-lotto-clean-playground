package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입금액은 숫자로 입력해야 합니다.");
        }
    }

    public static int getManualCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("수동 구매 수는 숫자로 입력해야 합니다.");
        }
    }

    public static List<List<String>> getManualLottoNumbers(int count) {
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<String>> rawLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            rawLottos.add(Arrays.asList(scanner.nextLine().split(",")));
        }
        return rawLottos;
    }

    public static List<String> getWinningNumbers() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Arrays.asList(scanner.nextLine().split(","));
    }

    public static int getBonusNumber() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 볼은 숫자로 입력해야 합니다.");
        }
    }
}
