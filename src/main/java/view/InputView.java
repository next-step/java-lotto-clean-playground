package view;

import domain.lotto.WinningLotto;
import domain.money.PurchaseAmount;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요.(쉼표 ','로 구분합니다.)";
    private static final String NUMBER_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public int readPurchaseAmount() {
        return readPurchaseAmountValue().value();
    }

    public PurchaseAmount readPurchaseAmountValue() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
        return PurchaseAmount.from(parsePurchaseAmount(scanner.nextLine()));
    }

    public WinningLotto readWinningLotto() {
        System.out.println();
        System.out.println(WINNING_NUMBERS_PROMPT);
        return WinningLotto.from(parseNumbers(scanner.nextLine()));
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(NUMBER_DELIMITER))
                .map(String::trim)
                .map(this::parseWinningNumber)
                .collect(Collectors.toList());
    }

    private int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    private int parseWinningNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("당첨 번호는 쉼표(,)로 구분한 숫자여야 합니다.");
        }
    }
}
