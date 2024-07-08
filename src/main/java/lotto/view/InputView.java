package lotto.view;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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

        return Arrays.stream(winNum.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public static int getBonusBall() {
        System.out.println("\n" + ConsoleMessage.INPUT_BONUS.getMessage());

        return Integer.parseInt(scanner.nextLine());
    }

    public static int getCustomLottoCount() {
        System.out.println("\n" + ConsoleMessage.CUSTOM_LOTTO_COUNT.getMessage());
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<List<Integer>> getCustomLotto(int customLottoCount) {
        System.out.println("\n" + ConsoleMessage.CUSTOM_LOTTO_INPUT.getMessage());
        String winNum;
        List<List<Integer>> customLottos = new ArrayList<>(new ArrayList<>());

        for (int i = 0; i < customLottoCount; i++) {
            winNum = scanner.nextLine();

            customLottos.add(Arrays.stream(winNum.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
        }

        return customLottos;
    }
}
