package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Integer readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = scanner.nextInt();
        scanner.nextLine();
        return amount;
    }

    public static String readWinningNumbers() {
        System.out.printf("%n지난 주 당첨 번호를 입력해 주세요.%n");
        return scanner.nextLine();
    }
}
