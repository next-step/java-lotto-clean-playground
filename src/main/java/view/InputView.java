package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public String readLottoPurchaseAmount() {
        return scanner.nextLine();
    }

    public String readLastWeekWinningNumbers() {
        return scanner.nextLine();
    }
}
