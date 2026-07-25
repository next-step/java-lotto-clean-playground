import domain.*;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import dto.PurchaseResult;

public class Application {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final LottoStore lottoStore = new LottoStore();
    private final LotteryStatistics lotteryStatistics = new LotteryStatistics();

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        PurchaseResult purchaseResult = inputPurchaseResult();
        showResult(purchaseResult);
    }

    private PurchaseResult purchaseLotto() {
        Money purchasePrice = inputPurchasePrice();
        lottoStore.validatePurchasePrice(purchasePrice);

        int manualCount = inputManualLottoCount();
        lottoStore.validateManualCount(purchasePrice, manualCount);

        List<Lotto> manualLottos = inputManualLottos(manualCount);

        Lottos lottos = purchaseLottos(purchasePrice, manualLottos);

        return new PurchaseResult(purchasePrice, lottos);
    }

    private PurchaseResult inputPurchaseResult() {
        while (true) {
            try {
                return purchaseLotto();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void publishStatistics(Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber, Money purchasePrice) {
        lotteryStatistics.calculateStatistics(lottos, winningLotto, bonusNumber);
        Money totalPrize = lotteryStatistics.calculatePrize();
        resultView.printStatistics(lotteryStatistics, totalPrize, purchasePrice);
    }

    private void showResult(PurchaseResult purchaseResult) {
        Lotto winningLotto = inputWinningLotto();
        LottoNumber bonusNumber = inputBonusNumber(winningLotto);
        publishStatistics(
                purchaseResult.lottos(),
                winningLotto,
                bonusNumber,
                purchaseResult.purchasePrice()
        );
    }

    private int inputManualLottoCount() {
        while (true) {
            try {
                return inputView.inputManualLottoCount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto inputManualLotto() {
        while (true) {
            try {
                String input = inputView.inputManualLotto();
                return createLotto(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Lotto> inputManualLottos(int manualCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(inputManualLotto());
        }
        return manualLottos;
    }

    private LottoNumber inputBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                LottoNumber bonusNumber = new LottoNumber(inputView.inputBonusNumber());
                winningLotto.validateBonusNumber(bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Money inputPurchasePrice() {
        while (true) {
            try {
                return new Money(inputView.inputPrice());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto createLotto(String input) {
        List<LottoNumber> numbers = parseLottoNumbers(input.split(","));
        return new Lotto(numbers);
    }

    private Lottos purchaseLottos(Money purchasePrice, List<Lotto> manualLottos) {
        Lottos lottos = lottoStore.buy(purchasePrice, manualLottos);
        resultView.printLottos(lottos, manualLottos.size());
        return lottos;
    }

    private Lotto inputWinningLotto() {
        while (true) {
            try {
                String input = inputView.inputWinningLotto();
                return createLotto(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<LottoNumber> parseLottoNumbers(String[] numbers) {
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
