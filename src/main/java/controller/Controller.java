package controller;

import domain.Lotto;
import domain.LottoMaker;
import domain.LottoPrice;
import domain.WinDiscriminator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Controller {

    public void purchaseLotto() {
        final int LOTTO_PRICE = 1_000;

        InputView inputView = new InputView();
        LottoPrice lottoPrice = LottoPrice.valueOf(inputView.readBudget());
        int manalLottoQuantity = inputView.readManualLottoQuantity();

        if (lottoPrice.divideByUnit() < manalLottoQuantity) {
            throw new IllegalArgumentException("구매 금액보다 많은 수동 로또를 살 수 없습니다.");
        }

        int autoLottoQuantity = lottoPrice.getBudget() / LOTTO_PRICE - manalLottoQuantity;

        List<List<Integer>> manualLottoNumbers = inputView.readManualLottosNumber(manalLottoQuantity);

        LottoMaker lottoMaker = new LottoMaker();
        List<Lotto> manualLottos = lottoMaker.manualMake(manalLottoQuantity, manualLottoNumbers);
        List<Lotto> autoLottos = lottoMaker.autoMake(autoLottoQuantity);
        manualLottos.addAll(autoLottos);
        List<Lotto> lottos = manualLottos;

        OutputView outputView = new OutputView();
        outputView.printLottoQuantity(manalLottoQuantity, autoLottoQuantity);
        outputView.printLottos(lottos);

        List<Integer> winNumbers = inputView.readWinLottoNumbers();
        int bonus = inputView.readBonusNumber();

        WinDiscriminator discriminator = new WinDiscriminator();
        discriminator.discriminateAll(winNumbers, lottos, bonus);

        outputView.printPrizeResult(discriminator, lottoPrice.getBudget());
    }
}
