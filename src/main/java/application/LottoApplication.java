package application;

import java.util.ArrayList;
import model.Lotto;
import model.LottoGenerator;
import model.LottoNumber;
import model.LottoPurchase;
import java.util.List;
import model.LottoStatistics;
import view.LottoView;

public class LottoApplication {
    private static final int LOTTO_PRICE = 1000;

    public static void main(String[] args) {
        LottoView view = new LottoView();
        int purchaseAmount = view.readPurchaseAmount();
        int manualLottoCount = view.readManualLottoCount();
        List<Lotto> manualLottos = view.readManualLottos(manualLottoCount);

        LottoPurchase purchase = new LottoPurchase(manualLottos, purchaseAmount);
        int autoLottoCount = purchase.getAutoLottoCount();
        view.printLottoCount(manualLottoCount, autoLottoCount);

        LottoGenerator generator = new LottoGenerator();
        List<Lotto> autoLottos = generator.generate(autoLottoCount);
        List<Lotto> allLotttos = new ArrayList<>();
        allLotttos.addAll(manualLottos);
        allLotttos.addAll(autoLottos);
        view.printLotto(allLotttos);

        List<LottoNumber> winningNumbers = view.readWinningNumbers();

        LottoNumber bonusNumber = view.readBonusNumber();
        LottoStatistics statistics = new LottoStatistics(allLotttos, winningNumbers, bonusNumber, purchaseAmount);
        view.printStatistics(statistics);
    }
}
