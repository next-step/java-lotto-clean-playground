package view;

import java.util.Scanner;

public class InputReader {

    private static final String PRICE_QUERY = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_QUERY = "지난 주 당첨 번호를 입력해 주세요.";

    public static int getPrice() {
        System.out.println(PRICE_QUERY);
        return readInt();
    }

    public static String  getWinningNumbers() {
        System.out.println("\n" + WINNING_NUMBERS_QUERY);
        return readString();
    }

    private static int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
