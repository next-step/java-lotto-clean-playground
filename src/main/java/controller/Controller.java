package controller;

import domain.Lotto;
import domain.LottoMaker;
import domain.WinDiscriminator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Controller {

    public void purchaseLotto() {
        LottoMaker lottoMaker = new LottoMaker();

        InputView inputView = new InputView();
        int budget = inputView.readBudget();
        int manualQuantity = inputView.readManualQuantity();
        int manualBudget = manualQuantity * 1000;
        int autoBudget = budget - manualBudget;

        List<List<Integer>> manualLottoNumbers= inputView.readManualLottoNumbers(manualQuantity);

        List<Lotto> lottos = lottoMaker.make(manualBudget, manualLottoNumbers);
        lottos.addAll(lottoMaker.make(autoBudget));

        OutputView outputView = new OutputView();
        outputView.printLottoQuantity(lottoMaker);
        outputView.printLottos(lottos);

        List<Integer> winNumbers = inputView.readWinLottoNumbers();
        int bonus = inputView.readBonusNumber();

        WinDiscriminator discriminator = new WinDiscriminator();
        discriminator.discriminateAll(winNumbers, lottos, bonus);

        outputView.printPrizeResult(discriminator, budget);
    }
}
