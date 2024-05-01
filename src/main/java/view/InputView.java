package view;

import java.util.Scanner;

public class InputView {

    private static final String PRICE_QUERY = "구입금액을 입력해 주세요.";

    public static int getPrice() {
        System.out.println(PRICE_QUERY);
        return readInt();
    }

    private static int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }





}
