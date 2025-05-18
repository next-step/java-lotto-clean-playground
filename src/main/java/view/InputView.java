package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.(1000원 단위)");
        return scanner.nextInt();
    }

    public static String inputWinningNumberForLastWeek() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        return scanner.nextLine();
    }

}
