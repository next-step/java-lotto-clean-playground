package view;

import domain.lotto.BonusBall;
import domain.lotto.Lotto;
import domain.lotto.Lottos;
import domain.lotto.ManualPurchaseCount;
import domain.lotto.WinningLotto;
import domain.money.PurchaseAmount;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
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

    public ManualPurchaseCount readManualPurchaseCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return ManualPurchaseCount.from(parseManualPurchaseCount(scanner.nextLine()));
    }

    public Lottos readManualLottos(ManualPurchaseCount manualPurchaseCount) {
        System.out.println();
        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.(장 별로는 Enter로 구분합니다.)");
        return new Lottos(readManualLottoValues(manualPurchaseCount));
    }

    private List<Lotto> readManualLottoValues(ManualPurchaseCount manualPurchaseCount) {
        return java.util.stream.IntStream.range(0, manualPurchaseCount.value())
                .mapToObj(index -> new Lotto(parseManualNumbers(scanner.nextLine())))
                .collect(Collectors.toList());
    }

    public WinningLotto readWinningLotto() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return WinningLotto.of(parseWinningNumbers(scanner.nextLine()), readBonusBall());
    }

    private BonusBall readBonusBall() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        return BonusBall.from(parseBonusBall(scanner.nextLine()));
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
