package util;

import java.util.Scanner;

public class InputFromUser {

    private static final Scanner scanner = new Scanner(System.in);

    public static int inputBuyingCosts() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static String inputLastWeekWinningLottoNumber() {
        return scanner.nextLine();
    }
}
