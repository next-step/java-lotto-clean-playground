package controller;

import java.util.ArrayList;
import java.util.List;
import model.Lotto;
import model.LottoGenerator;
import model.LottoNumber;
import model.LottoPurchase;
import model.LottoStatistics;
import view.LottoView;

public class LottoController {
    private final LottoView view;

    public LottoController(LottoView view) {
        this.view = view;
    }

    public void run() {
        LottoView view = new LottoView();
        int purchaseAmount = view.readPurchaseAmount();
        List<Lotto> manualLottos = getManualLottos(view);
        LottoPurchase purchase = new LottoPurchase(manualLottos, purchaseAmount);

        List<Lotto> allLottos = getAllLottos(view, manualLottos, purchase);
        LottoStatistics statistics = createStatistics(view, allLottos, purchaseAmount);

        view.printStatistics(statistics);
    }

    private List<Lotto> getManualLottos(LottoView view) {
        int manualCount = view.readManualLottoCount();
        return view.readManualLottos(manualCount);
    }

    private List<Lotto> getAllLottos(LottoView view, List<Lotto> manualLottos, LottoPurchase purchase) {
        int manualCount = manualLottos.size();
        int autoCount = purchase.getAutoLottoCount();

        view.printLottoCount(manualCount, autoCount);

        LottoGenerator generator = new LottoGenerator();
        List<Lotto> autoLottos = generator.generate(autoCount);

        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manualLottos);
        allLottos.addAll(autoLottos);

        view.printLotto(allLottos);
        return allLottos;
    }

    private LottoStatistics createStatistics(LottoView view, List<Lotto> allLottos, int purchaseAmount) {
        List<LottoNumber> winningNumbers = view.readWinningNumbers();
        LottoNumber bonusNumber = view.readBonusNumber();
        return new LottoStatistics(allLottos, winningNumbers, bonusNumber, purchaseAmount);
    }
}

