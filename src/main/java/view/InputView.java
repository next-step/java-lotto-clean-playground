package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다. 입력값: " + input);
        }
    }

    public static int readManualSelectionCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine().trim();
        return Integer.parseInt(input);
    }

    public static List<Integer> readManualSelection() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(InputView::parseNumber)
                .toList();
    }

    public static List<Integer> readWinnerNumber() {
        System.out.println("지난 주 당첨번호를 입력해 주세요.");
        return Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(InputView::parseNumber)
                .toList();

    }

    public static int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine().trim();
        return Integer.parseInt(input);
    }

    private static int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 로또 당첨 번호는 숫자여야 합니다. 잘못된 입력값: " + value);
        }
    }


}
