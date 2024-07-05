package lotto.view;

import java.util.Scanner;

import lotto.message.ConsoleMessage;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getMoney(){
        System.out.println(ConsoleMessage.INPUT_MONEY.getMessage());
        return scanner.nextInt();
    }
}
