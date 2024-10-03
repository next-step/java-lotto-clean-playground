import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.LottoStatisticData;
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
            int amount = inputView.readPurchaseAmount();
            int lottoCount = amount / Lotto.PRICE;

            int manualCount = inputView.readManualCount();

            if (lottoCount < manualCount) {
                throw new IllegalArgumentException("내신 금액보다 많은 로또를 구매하려고 합니다.");
            }

            int automaticCount = lottoCount - manualCount;

            List<Lotto> lottos = purchaseLottos(automaticCount, manualCount);

            outputView.printPurchasedCount(automaticCount, manualCount);
            outputView.printLottosNumbers(lottos);

            LottoNumbers winnerNumbers = inputView.readWinnerNumbers();
            LottoNumber bonusNumber = inputView.readBonusNumber();


            LottoStatisticData lottoStatisticData = new LottoStatisticData(winnerNumbers, bonusNumber, lottos);

            outputView.printStatistic(lottoStatisticData.getMatchStatistic());

            double earnRate = lottoStatisticData.calculateRate(amount);
            outputView.printEarnRate(earnRate);




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
}
