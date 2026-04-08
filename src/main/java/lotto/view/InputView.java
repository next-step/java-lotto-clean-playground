package lotto.view;

import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NOT_NUMBER_ERROR = "[ERROR] 숫자만 입력 가능합니다.";

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    public static int inputManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    public static void printManualInputMessage() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }

    public static List<Integer> inputNumbers() {
        String input = scanner.nextLine();
        // 쉼표로 구분된 각 값이 숫자인지 검증
        Arrays.stream(input.split(","))
                .map(String::trim)
                .forEach(InputView::validateNumeric);

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return inputNumbers();
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        validateNumeric(input); // ⭐ 파싱 전 검증
        return Integer.parseInt(input);
    }

    private static void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR);
        }
    }
}
