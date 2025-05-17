package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String ERROR_NOT_NUMBER = "숫자를 입력해 주세요.";
    public static final String DELIMITER = ",";
    private final Scanner scanner = new Scanner(System.in);

    public int readAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT);
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }

    public List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println(INPUT_WINNING_NUMBERS);
        String input = scanner.nextLine();
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

    public int readBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }
}
