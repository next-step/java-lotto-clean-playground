package controller;

import domain.*;
import enums.LottoRank;
import domain.LottoShop;
import enums.LottoType;
import view.LottoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoController {

    private final LottoShop lottoShop;
    private final LottoView lottoView;

    public LottoController(LottoShop lottoShop, LottoView lottoView) {
        this.lottoShop = lottoShop;
        this.lottoView = lottoView;
    }

    public void run() {
        Lottos lottos = PurchaseLottos();
        LottoWinningNumbers lottoWinningNumbers = inputWinningNumbersAndBonus();
        LottoResult lottoResult = LottoResult.createLottoResult(lottoWinningNumbers, lottos);
        printResultByRank(lottoResult.getResultByRank());
        printProfitRate(lottoResult.getResultByRank(), lottos.getLottoCount());
    }

    private void printProfitRate(Map<LottoRank, Integer> resultByRank, LottoCount lottoCount) {
        double profitRate = ProfitCalculator.calculateProfitRate(resultByRank, lottoCount);
        lottoView.printProfitRate(profitRate);
    }

    private void printResultByRank(Map<LottoRank, Integer> resultByRank) {
        lottoView.printResultByRanks(resultByRank);
    }

    private LottoWinningNumbers inputWinningNumbersAndBonus() {
        List<Integer> inputNumbers = lottoView.inputWinningNumbersView();
        int inputBonusNumber = lottoView.inputBonusNumberView();
        return LottoWinningNumbers.from(inputNumbers, inputBonusNumber);

    }

    private Lottos PurchaseLottos() {
        Money money = Money.from(lottoView.inputPurchaseAmount());
        LottoCount manualLottoCount = LottoCount.from(lottoView.inputManualLottoCount());
        List<Lotto> manualLottos = inputManualLottos(manualLottoCount);

        Lottos lottos = lottoShop.purchaseLottos(money, manualLottoCount, manualLottos);
        lottoView.printPurchaseInfo(lottos);

        return lottos;
    }

    private List<Lotto> inputManualLottos(LottoCount manualLottoCount) {
        List<Lotto> manualLottos = new ArrayList<>();
        List<List<Integer>> inputLottos = lottoView.inputManualLottos(manualLottoCount);
        for (List<Integer> inputLotto : inputLottos) {
            manualLottos.add(Lotto.from(inputLotto, LottoType.MANUAL));
        }
        return manualLottos;
    }
}
