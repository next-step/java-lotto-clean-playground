package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static int inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요.");
        }
    }

    public static String inputWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();

        if (input.isBlank()) {
            throw new IllegalArgumentException("당첨 번호를 입력해 주세요.");
        }
        return input;
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요.");
        }
    }

    public static int inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요.");
        }
    }

    public static String inputManualLotto() {
        String input = scanner.nextLine();

        if (input.isBlank()) {
            throw new IllegalArgumentException("수동으로 구매할 번호를 다시 입력해 주세요.");
        }
        return input;
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
