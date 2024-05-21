package view;

import domain.Lotto;
import domain.LottoPrice;
import domain.WinningNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static view.OutputView.*;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static final String LOTTO_PRICE_INPUT_ERROR_MESSAGE = "로또 구입금액은 유효한 숫자여야 합니다.";


    public static int readLottoPrice() {
        String input = scanner.nextLine();
        try {
            return LottoPrice.valueOf(input).price();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(LOTTO_PRICE_INPUT_ERROR_MESSAGE);
        }
    }

    public static List<List<Integer>> inputManualNumbers(int manualCount) {
        printInputManualNumbersMessage();
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualNumbers.add(inputSingleManualNumbers());
        }
        return manualNumbers;
    }

    public static int inputManualCount() {
        printInputManualCountMessage();
        int manualCount = scanner.nextInt();
        scanner.nextLine();
        return manualCount;
    }

    private static List<Integer> inputSingleManualNumbers() {
        String input = scanner.nextLine().trim();
        return parseNumbers(input);
    }

    public static int inputBonusBall() {
        printInputBonusBallMessage();
        int bonusBall = scanner.nextInt();
        scanner.nextLine();
        return bonusBall;
    }

    public static List<Integer> inputWinningNumbers() {
        printInputWinningNumbersMessage();
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return List.of();
        }

        return parseNumbers(input);
    }

    private static List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}