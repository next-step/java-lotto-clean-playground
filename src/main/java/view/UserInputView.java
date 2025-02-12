package view;

import java.util.Scanner;

public class UserInputView {

    private static final Scanner scanner = new Scanner(System.in);

    private UserInputView() {
    }

    public static String readStringInput() {
        return scanner.nextLine();
    }

    public static long readLongInput() {
        return Long.parseLong(scanner.nextLine());
    }

    public static int readIntInput() {
        return Integer.parseInt(scanner.nextLine());
    }
}
