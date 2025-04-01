package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static long readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = SCANNER.nextLine().trim();
        return Long.parseLong(input);
    }

    public static String readWinningNumbers() {
        System.out.printf("%n지난 주 당첨 번호를 입력해 주세요.%n");
        return SCANNER.nextLine();
    }

    public static int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = SCANNER.nextLine().trim();
        return Integer.parseInt(input);
    }
}
