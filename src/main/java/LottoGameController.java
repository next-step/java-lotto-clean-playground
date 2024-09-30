import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.WinnerStatistic;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

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


            WinnerStatistic winnerStatistic = new WinnerStatistic(winnerNumbers, bonusNumber, lottos);

            outputView.printStatistic(winnerStatistic.getMatch());

            double earnRate = winnerStatistic.calculateRate(amount);
            outputView.printEarnRate(earnRate);




        } catch (Exception e) {
            System.out.println("err");
            System.out.println(e.getMessage());
        }




    }

    private List<Lotto> purchaseLottos(int autoCount, int manualCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            LottoNumbers lottoNumbers = inputView.readManualLottoNumbers(i);
            lottos.add(new Lotto(lottoNumbers));
        }

        System.out.println();

        for (int i = 0; i < autoCount; i++) {
            Lotto lotto = Lotto.creatAutoLotto();
            lottos.add(lotto);
        }

        return lottos;
    }
}
