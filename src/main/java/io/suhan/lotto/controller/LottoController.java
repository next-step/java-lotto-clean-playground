package io.suhan.lotto.controller;

import static io.suhan.lotto.model.executor.PurchaseExecutor.PRICE_PER_LOTTO;

import io.suhan.lotto.model.executor.DrawExecutor;
import io.suhan.lotto.model.executor.PurchaseExecutor;
import io.suhan.lotto.model.lotto.Lotto;
import io.suhan.lotto.model.lotto.LottoFactory;
import io.suhan.lotto.model.lotto.LottoNumber;
import io.suhan.lotto.model.lotto.LottoRegistry;
import io.suhan.lotto.model.lotto.LottoStatistics;
import io.suhan.lotto.view.InputView;
import io.suhan.lotto.view.OutputView;
import java.util.Set;

public class LottoController {
    private final LottoRegistry registry;

    public LottoController() {
        this.registry = new LottoRegistry();
    }

    public void run() {
        try {
            int balance = InputView.getValidBalance();

            executePurchase(balance);
            executeDraw(balance);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void executePurchase(int balance) {
        int manualCount = InputView.getValidManualCount();

        if (PRICE_PER_LOTTO * manualCount > balance) {
            throw new IllegalArgumentException("금액이 부족합니다.");
        }

        PurchaseExecutor purchaseExecutor = new PurchaseExecutor(registry, balance, manualCount);
        purchaseExecutor.execute();

        OutputView.printPurchaseResult(registry.getLottos());
    }

    private void executeDraw(int balance) {
        Lotto winningLotto = createWinningLotto();

        LottoNumber bonusNumber = InputView.getValidBonusNumber();

        DrawExecutor drawExecutor = new DrawExecutor(registry, winningLotto, bonusNumber);
        drawExecutor.execute();

        LottoStatistics statistics = new LottoStatistics(drawExecutor.getResults());

        OutputView.printStatistics(statistics, balance);
    }

    private Lotto createWinningLotto() {
        Set<LottoNumber> wonNumbers = LottoFactory.toLottoNumbers(InputView.getValidWonNumbers());

        return Lotto.of(wonNumbers);
    }
}
