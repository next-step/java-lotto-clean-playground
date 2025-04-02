package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static long readPurchaseAmount() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            String input = scanner.nextLine().trim();

            if (!input.matches("\\d+")) {
                System.out.println("유효한 정수를 입력하세요.");
                continue;
            }

            return Long.parseLong(input);
        }
    }

    public static String readWinningNumbers() {
        System.out.printf("%n지난 주 당첨 번호를 입력해 주세요.%n");
        return scanner.nextLine();
    }
}
