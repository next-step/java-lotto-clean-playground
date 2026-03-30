package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public String readInput() {
        return scanner.nextLine();
    }
}
