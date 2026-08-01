package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return parsePurchaseAmount(scanner.nextLine());
    }

    public int readManualPurchaseCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return parseManualPurchaseCount(scanner.nextLine());
    }

    public List<List<Integer>> readManualLottoNumbers(int manualPurchaseCount) {
        System.out.println();
        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.(장 별로는 Enter로 구분합니다.)");
        return IntStream.range(0, manualPurchaseCount)
                .mapToObj(index -> parseManualNumbers(scanner.nextLine()))
                .collect(Collectors.toList());
    }

    public List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return parseWinningNumbers(scanner.nextLine());
    }

    public int readBonusBall() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        return parseBonusBall(scanner.nextLine());
    }

    private List<Integer> parseManualNumbers(String input) {
        return parseNumbers(input, this::parseManualNumber);
    }

    private List<Integer> parseWinningNumbers(String input) {
        return parseNumbers(input, this::parseWinningNumber);
    }

    private List<Integer> parseNumbers(String input, Function<String, Integer> parser) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(parser)
                .collect(Collectors.toList());
    }

    private int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    private int parseManualPurchaseCount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("수동 구매 수는 숫자여야 합니다.");
        }
    }

    private int parseManualNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("수동 구매 번호는 쉼표(,)로 구분한 숫자여야 합니다.");
        }
    }

    private int parseWinningNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("당첨 번호는 쉼표(,)로 구분한 숫자여야 합니다.");
        }
    }

    private int parseBonusBall(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("보너스 볼은 숫자여야 합니다.");
        }
    }
}
