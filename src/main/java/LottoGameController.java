import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        try {
            LottoShop lottoShop = new LottoShop();

            int amount = inputView.readPurchaseAmount();

            int lottoCount = lottoShop.calculateLottoCount(amount);
            int manualCount = inputView.readManualCount(lottoCount);
            int automaticCount = lottoCount - manualCount;

            List<Lotto> lottos = purchaseLottos(automaticCount, manualCount);

            printPurchasedLottosInfo(automaticCount, manualCount, lottos);

            List<LottoNumber> winnerNumbers = inputView.readWinnerNumbers();
            LottoNumber bonusNumber = inputView.readBonusNumber();

            LottoStatisticData lottoStatisticData = new LottoStatisticData(winnerNumbers, bonusNumber, lottos);

            printStatisticResult(lottoStatisticData, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Lotto> purchaseLottos(int autoCount, int manualCount) {
        if (manualCount > 0) {
            inputView.printReadManualLottoMessage();
        }

        List<Lotto> manualLottos = IntStream.range(0, manualCount)
                .mapToObj(i -> inputView.readManualLottoNumbers())
                .map(Lotto::new)
                .toList();

        System.out.println();

        List<Lotto> autoLottos = IntStream.range(0, autoCount)
                .mapToObj(i -> Lotto.creatAutoLotto())
                .toList();

        return new ArrayList<>() {{
            addAll(manualLottos);
            addAll(autoLottos);
        }};
    }

    private void printStatisticResult(LottoStatisticData lottoStatisticData, int amount) {
        outputView.printStatistic(lottoStatisticData.getMatchStatistic());
        double earnRate = lottoStatisticData.calculateRate(amount);
        outputView.printEarnRate(earnRate);
    }

    private void printPurchasedLottosInfo(int automaticCount, int manualCount, List<Lotto> lottos) {
        outputView.printPurchasedCount(automaticCount, manualCount);
        outputView.printLottosNumbers(lottos);
    }
}
