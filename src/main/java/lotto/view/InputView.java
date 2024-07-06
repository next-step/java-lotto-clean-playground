package lotto.view;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import lotto.message.ConsoleMessage;

public class InputView {

    private InputView() {
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static int getMoney() {
        System.out.println(ConsoleMessage.INPUT_MONEY.getMessage());
        Integer inputMoney = Integer.parseInt(scanner.nextLine());
        return inputMoney;
    }

    public static List<Integer> getWinLotto() {
        System.out.println("\n" + ConsoleMessage.LAST_WIN_NUM.getMessage());
        String winNum = scanner.nextLine();
        List<String> splitString = List.of(winNum.split(","));
        List<Integer> winNums = new ArrayList<>();

        for (String num : splitString) {
            winNums.add(Integer.parseInt(num));
        }

        return winNums;
    }

    public static int getBonusBall() {
        System.out.println("\n" + ConsoleMessage.INPUT_BONUS.getMessage());

        return Integer.parseInt(scanner.nextLine());
    }
}
