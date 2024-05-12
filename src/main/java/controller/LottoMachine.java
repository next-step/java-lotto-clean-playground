package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoMachine {

    public void purchaseLotto() {
        final int LOTTO_PRICE = 1_000;

        InputView inputView = new InputView();
        BudgetInfo budgetInfo = BudgetInfo.valueOf(inputView.readBudget());
        int manalLottoQuantity = inputView.readManualLottoQuantity();

        if (budgetInfo.budget() < manalLottoQuantity * LOTTO_PRICE) {
            throw new IllegalArgumentException("구매 금액보다 많은 수동 로또를 살 수 없습니다.");
        }

        int autoLottoQuantity = budgetInfo.budget() / LOTTO_PRICE - manalLottoQuantity;

        List<List<LottoNumber>> manualLottoNumbers = inputView.readManualLottosNumber(manalLottoQuantity);

        LottoMaker lottoMaker = new LottoMaker();
        List<Lotto> manualLottos = lottoMaker.manualMake(manalLottoQuantity, manualLottoNumbers);
        List<Lotto> autoLottos = lottoMaker.autoMake(autoLottoQuantity);
        manualLottos.addAll(autoLottos);
        List<Lotto> lottos = manualLottos;

        OutputView outputView = new OutputView();
        outputView.printLottoQuantity(manalLottoQuantity, autoLottoQuantity);
        outputView.printLottos(lottos);

        List<LottoNumber> winNumbers = inputView.readWinLottoNumbers();
        // 보너스 번호 검증 필요
        BonusNumber bonus = new BonusNumber(inputView.readBonusNumber(), winNumbers);

        WinDiscriminator discriminator = new WinDiscriminator();
        discriminator.discriminateAll(winNumbers, lottos, bonus.getBonusNumber());

        outputView.printPrizeResult(budgetInfo.budget());
    }
}
