package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println("구입금액을 입력해주세요.");
        int money = scanner.nextInt();
        scanner.nextLine();
        return money;
    }
}
