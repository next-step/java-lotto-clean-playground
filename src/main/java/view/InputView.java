package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    public static int readPrice(Scanner scanner) {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다. 입력값: " + input);
        }
    }

    public static List<Integer> readWinnerNumber(Scanner scanner) {
        System.out.println("지난 주 당첨번호를 입력해 주세요.");
        return Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(InputView::parseNumber)
                .toList();

    }

    private static int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 로또 당첨 번호는 숫자여야 합니다. 잘못된 입력값: " + value);
        }
    }
}
