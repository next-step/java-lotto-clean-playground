package application;

import model.Lotto;
import model.LottoGenerator;
import model.LottoNumber;
import model.LottoPurchase;
import java.util.List;
import model.LottoStatistics;
import view.LottoView;

public class LottoApplication {
    public static void main(String[] args) {
        LottoView view = new LottoView();
        int purchaseAmount = view.readPurchaseAmount();

        LottoPurchase purchase = new LottoPurchase(purchaseAmount);
        int lottoCount = purchase.getLottoCount();
        view.printLottoCount(lottoCount);

        LottoGenerator generator = new LottoGenerator();
        List<Lotto> generatedLottos = generator.generate(lottoCount);

        view.printLotto(generatedLottos);

        List<LottoNumber> winningNumbers = view.readWinningNumbers();

        LottoStatistics statistics = new LottoStatistics(generatedLottos, winningNumbers, purchaseAmount);
        view.printStatistics(statistics);
    }
}
