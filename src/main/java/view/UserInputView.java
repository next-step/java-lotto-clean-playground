package view;

import java.util.Scanner;

public class UserInputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static long readLongInput() {
        return scanner.nextLong();
    }
}
