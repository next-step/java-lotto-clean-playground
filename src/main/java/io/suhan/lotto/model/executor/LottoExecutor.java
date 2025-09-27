package io.suhan.lotto.model.executor;

import io.suhan.lotto.model.lotto.Lotto;
import io.suhan.lotto.model.lotto.LottoNumber;
import io.suhan.lotto.model.lotto.LottoRegistry;
import io.suhan.lotto.model.lotto.LottoStatistics;
import io.suhan.lotto.view.InputView;
import io.suhan.lotto.view.OutputView;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoExecutor implements Executor {
    private final LottoRegistry registry;

    public LottoExecutor() {
        this.registry = new LottoRegistry();
    }

    @Override
    public void execute() {
        try {
            int balance = InputView.getBalance();

            executePurchase(balance);
            executeDraw(balance);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void executePurchase(int balance) {
        PurchaseExecutor purchaseExecutor = new PurchaseExecutor(registry, balance);
        purchaseExecutor.execute();

        OutputView.printPurchaseResult(registry.getLottos());
    }

    private void executeDraw(int balance) {
        Lotto winningLotto = createWinningLotto();

        DrawExecutor drawExecutor = new DrawExecutor(registry, winningLotto);
        drawExecutor.execute();

        LottoStatistics statistics = new LottoStatistics(drawExecutor.getResults());

        OutputView.printStatistics(statistics, balance);
    }

    private Lotto createWinningLotto() {
        Set<LottoNumber> wonNumbers = InputView.getWonNumbers()
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());

        return new Lotto(wonNumbers);
    }
}
