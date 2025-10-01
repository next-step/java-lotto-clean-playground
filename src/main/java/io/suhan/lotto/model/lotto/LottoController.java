package io.suhan.lotto.model.lotto;

import static io.suhan.lotto.model.executor.PurchaseExecutor.PRICE_PER_LOTTO;

import io.suhan.lotto.model.executor.DrawExecutor;
import io.suhan.lotto.model.executor.PurchaseExecutor;
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
            int balance = InputView.getBalance();

            if (balance < PRICE_PER_LOTTO) {
                throw new IllegalArgumentException("금액은 " + PRICE_PER_LOTTO + "원 보다 커야 합니다.");
            }

            executePurchase(balance);
            executeDraw(balance);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void executePurchase(int balance) {
        int manualCount = InputView.getManualCount();

        if (manualCount < 0) {
            throw new IllegalArgumentException("로또 수는 0 또는 양수만 입력할 수 있습니다.");
        }

        if (PRICE_PER_LOTTO * manualCount > balance) {
            throw new IllegalArgumentException("금액이 부족합니다.");
        }

        PurchaseExecutor purchaseExecutor = new PurchaseExecutor(registry, balance, manualCount);
        purchaseExecutor.execute();

        OutputView.printPurchaseResult(registry.getLottos());
    }

    private void executeDraw(int balance) {
        Lotto winningLotto = createWinningLotto();

        LottoNumber bonusNumber = new LottoNumber(InputView.getBonusNumber());

        DrawExecutor drawExecutor = new DrawExecutor(registry, winningLotto, bonusNumber);
        drawExecutor.execute();

        LottoStatistics statistics = new LottoStatistics(drawExecutor.getResults());

        OutputView.printStatistics(statistics, balance);
    }

    private Lotto createWinningLotto() {
        Set<LottoNumber> wonNumbers = LottoFactory.toLottoNumbers(InputView.getWonNumbers());

        return Lotto.of(wonNumbers);
    }
}
