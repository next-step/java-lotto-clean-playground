package view;

import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ", ";

    public static int inputMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요");
        return sc.nextInt();
    }

    public static String[] inputWinningLotto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        final String input = sc.nextLine();
        return input.split(DELIMITER);
    }
}
