package view;

import domain.lotto.BonusBall;
import domain.lotto.WinningLotto;
import domain.money.PurchaseAmount;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int readPurchaseAmount() {
        return readPurchaseAmountValue().value();
    }

    public PurchaseAmount readPurchaseAmountValue() {
        System.out.println("구입금액을 입력해 주세요.");
        return PurchaseAmount.from(parsePurchaseAmount(scanner.nextLine()));
    }

    public WinningLotto readWinningLotto() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return WinningLotto.of(parseNumbers(scanner.nextLine()), readBonusBall());
    }

    private BonusBall readBonusBall() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        return BonusBall.from(parseBonusBall(scanner.nextLine()));
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
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

    private int parseBonusBall(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("보너스 볼은 숫자여야 합니다.");
        }
    }
}
