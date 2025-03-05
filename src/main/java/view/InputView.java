package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int purchaseAmountTitle() {
        System.out.println("구입금액을 입력해 주세요.\n");

        return scanner.nextInt();
    }

    public static void closeScanner() {
        scanner.close();
    }

}
