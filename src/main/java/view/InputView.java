package view;

import java.util.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readBuyMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        return scanner.nextInt();
    }
}
