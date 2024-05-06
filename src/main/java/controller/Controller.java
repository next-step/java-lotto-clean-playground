package controller;

import domain.*;
import view.*;

import java.util.List;

public class Controller {
    public void purchaseLotto() {
        final int LOTTO_PRICE = 1_000;

        InputView inputView = new InputView();
        int budget = inputView.readBudget();
        int manalLottoQuantity = inputView.readManualLottoQuantity();

        int manualBudget = manalLottoQuantity * LOTTO_PRICE;
        int autoBudget = budget - manualBudget;
        int autoLottoQuantity = autoBudget / LOTTO_PRICE;

        List<List<Integer>> manualLottoNumbers = inputView.readManualLottosNumber(manalLottoQuantity);

        LottoMaker lottoMaker = new LottoMaker();
        List<Lotto> manualLottos = lottoMaker.manualMake(manualBudget, manualLottoNumbers);
        List<Lotto> autoLottos = lottoMaker.autoMake(autoBudget);
        manualLottos.addAll(autoLottos);
        List<Lotto> lottos = manualLottos;

        OutputView outputView = new OutputView();
        outputView.printLottoQuantity(manalLottoQuantity,autoLottoQuantity);
        outputView.printLottos(lottos);

        List<Integer> winNumbers = inputView.readWinLottoNumbers();
        int bonus = inputView.readBonusNumber();

        WinDiscriminator discriminator = new WinDiscriminator();
        discriminator.discriminateAll(winNumbers, lottos, bonus);

        outputView.printPrizeResult(discriminator, budget);
    }
}
