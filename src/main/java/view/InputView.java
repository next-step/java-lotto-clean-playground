package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static long getLong() {
        return Long.parseLong(scanner.nextLine());
    }

    public static int getInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static String getString() {
        return scanner.nextLine();
    }
}
