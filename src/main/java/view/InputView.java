package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String ERROR_NOT_NUMBER = "숫자를 입력해 주세요.";
    public static final String DELIMITER = ",";
    private final Scanner scanner = new Scanner(System.in);

    public int readAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT);
        String input = scanner.nextLine();
        return parseIntInput(input);
    }

    public List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println(INPUT_WINNING_NUMBERS);
        String input = scanner.nextLine();
        return parseNumbers(input);
    }

    public int readBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
        String input = scanner.nextLine();
        return parseIntInput(input);
    }

    public int readManualLottoCount() {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT);
        String input = scanner.nextLine();
        return parseIntInput(input);
    }

    public List<List<Integer>> readManualNumbers(int count) {
        List<List<Integer>> numbers = new ArrayList<>();
        System.out.println(INPUT_MANUAL_NUMBERS);
        for (int i = 0; i < count; i++) {
            String input = scanner.nextLine();
            numbers.add(parseNumbers(input));
        }
        return numbers;
    }

    private int parseIntInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }

    private static List<Integer> parseNumbers(String input) {
        List<String> stringList = Arrays.asList(input.split(DELIMITER));
        try {
            return stringList.stream()
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }
}
