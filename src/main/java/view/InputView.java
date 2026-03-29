package view;

import constants.ScriptConstants;

import java.util.Scanner;

public class InputView {
    Scanner scanner;
    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getUserCashInput() {
        System.out.println(ScriptConstants.INPUT_CASH_SCRIPT);
        return scanner.nextInt();
    }
}
