import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.LottoStore;
import domain.lotto.Lottos;
import domain.lotto.Money;
import domain.lotto.WinningLotto;
import domain.lotto.WinningResult;
import dto.PurchaseResult;
import view.InputView;
import view.LottoNumberParser;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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
        int totalCount = LottoStore.calculatePurchasableCount(purchasePrice);

        int manualCount = inputManualLottoCount();
        LottoStore.validateManualCount(totalCount, manualCount);

        List<Lotto> manualLottos = inputManualLottos(manualCount);

        Lottos lottos = purchaseLottos(totalCount, manualLottos);

        return new PurchaseResult(purchasePrice, lottos);
    }

    private static PurchaseResult inputPurchaseResult() {
        return retry(Application::purchaseLotto);
    }

    private static void showResult(PurchaseResult purchaseResult) {
        WinningLotto winningLotto = inputWinningLottoInfo();
        publishStatistics(
                purchaseResult.lottos(),
                winningLotto,
                purchaseResult.purchasePrice()
        );
    }

    private static void publishStatistics(Lottos lottos, WinningLotto winningLotto, Money purchasePrice) {
        WinningResult winningResult = lottos.matchRanks(winningLotto);
        Money totalPrize = winningResult.calculatePrize();
        ResultView.printStatistics(winningResult, totalPrize, purchasePrice);
    }

    private static WinningLotto inputWinningLottoInfo() {
        Lotto winningLotto = inputWinningLotto();
        return retry(() -> new WinningLotto(winningLotto, LottoNumber.from(InputView.inputBonusNumber())));
    }

    private static Lotto inputWinningLotto() {
        return retry(() -> new Lotto(LottoNumberParser.parse(InputView.inputWinningLotto())));
    }

    private static int inputManualLottoCount() {
        return retry(InputView::inputManualLottoCount);
    }

    private static Lotto inputManualLotto() {
        return retry(() -> new Lotto(LottoNumberParser.parse(InputView.inputManualLotto())));
    }

    private static List<Lotto> inputManualLottos(int manualCount) {
        InputView.printManualLottoGuide();

        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(inputManualLotto());
        }
        return manualLottos;
    }

    private static Money inputPurchasePrice() {
        return retry(() -> Money.from(InputView.inputPrice()));
    }

    private static Lottos purchaseLottos(int totalCount, List<Lotto> manualLottos) {
        Lottos lottos = LottoStore.buy(totalCount, manualLottos);
        ResultView.printLottos(lottos, manualLottos.size());
        return lottos;
    }

    private static <T> T retry(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                InputView.printErrorMessage(e);
            }
        }
    }
}
