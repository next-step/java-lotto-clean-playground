package io.suhan.lotto.model.lotto;

import io.suhan.lotto.model.executor.LottoDrawExecutor;
import io.suhan.lotto.model.executor.LottoPurchaseExecutor;
import io.suhan.lotto.view.InputView;
import io.suhan.lotto.view.OutputView;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoController {
    private final LottoRegistry registry;

    public LottoController() {
        this.registry = new LottoRegistry();
    }

    public void executePurchase(int balance) {
        LottoPurchaseExecutor purchaseExecutor = new LottoPurchaseExecutor(registry, balance);
        purchaseExecutor.execute();

        OutputView.printPurchaseResult(registry.getLottos());
    }

    public void executeDraw(int balance) {
        Lotto winningLotto = createWinningLotto();

        LottoDrawExecutor drawExecutor = new LottoDrawExecutor(registry, winningLotto);
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
