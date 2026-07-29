import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;
import domain.lotto.LottoStore;
import domain.lotto.Money;
import domain.lotto.LotteryStatistics;

import domain.lotto.WinningResult;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import dto.PurchaseResult;

public class Application {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        PurchaseResult purchaseResult = inputPurchaseResult();
        showResult(purchaseResult);
    }

    private static PurchaseResult purchaseLotto() {
        Money purchasePrice = inputPurchasePrice();
        LottoStore.validatePurchasePrice(purchasePrice);

        int manualCount = inputManualLottoCount();
        LottoStore.validateManualCount(purchasePrice, manualCount);

        List<Lotto> manualLottos = inputManualLottos(manualCount);

        Lottos lottos = purchaseLottos(purchasePrice, manualLottos);

        return new PurchaseResult(purchasePrice, lottos);
    }

    private static PurchaseResult inputPurchaseResult() {
        while (true) {
            try {
                return purchaseLotto();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void publishStatistics(Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber, Money purchasePrice) {
        WinningResult winningResult = lottos.matchRanks(winningLotto, bonusNumber);
        LotteryStatistics lotteryStatistics = new LotteryStatistics(winningResult);
        Money totalPrize = lotteryStatistics.calculatePrize();
        ResultView.printStatistics(lotteryStatistics, totalPrize, purchasePrice);
    }

    private static void showResult(PurchaseResult purchaseResult) {
        Lotto winningLotto = inputWinningLotto();
        LottoNumber bonusNumber = inputBonusNumber(winningLotto);
        publishStatistics(
                purchaseResult.lottos(),
                winningLotto,
                bonusNumber,
                purchaseResult.purchasePrice()
        );
    }

    private static int inputManualLottoCount() {
        while (true) {
            try {
                return InputView.inputManualLottoCount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Lotto inputManualLotto() {
        while (true) {
            try {
                String input = InputView.inputManualLotto();
                return createLotto(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static List<Lotto> inputManualLottos(int manualCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(inputManualLotto());
        }
        return manualLottos;
    }

    private static LottoNumber inputBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());
                winningLotto.validateBonusNumber(bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Money inputPurchasePrice() {
        while (true) {
            try {
                return new Money(InputView.inputPrice());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Lotto createLotto(String input) {
        List<LottoNumber> numbers = parseLottoNumbers(input.split(","));
        return new Lotto(numbers);
    }

    private static Lottos purchaseLottos(Money purchasePrice, List<Lotto> manualLottos) {
        Lottos lottos = LottoStore.buy(purchasePrice, manualLottos);
        ResultView.printLottos(lottos, manualLottos.size());
        return lottos;
    }

    private static Lotto inputWinningLotto() {
        while (true) {
            try {
                String input = InputView.inputWinningLotto();
                return createLotto(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static List<LottoNumber> parseLottoNumbers(String[] numbers) {
        try {
            return Arrays.stream(numbers)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }
}
