package view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String purchaseAmountTitle() {
        System.out.println("구입금액을 입력해 주세요.\n");
        String input = scanner.nextLine();
        scanner.close();

        return input;
    }
}
