package view;

import java.util.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readBuyMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        int money = scanner.nextInt();
        scanner.nextLine();
        return money;
    }

    public static String readWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }
}
