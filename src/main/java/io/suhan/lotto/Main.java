package io.suhan.lotto;

import io.suhan.lotto.model.DrawResult;
import io.suhan.lotto.model.lotto.Lotto;
import io.suhan.lotto.model.lotto.LottoNumber;
import io.suhan.lotto.model.lotto.LottoRegistry;
import io.suhan.lotto.model.executor.LottoDrawExecutor;
import io.suhan.lotto.model.executor.LottoPurchaseExecutor;
import io.suhan.lotto.view.InputView;
import io.suhan.lotto.view.OutputView;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            LottoRegistry registry = new LottoRegistry();

            int balance = InputView.getBalance();
            LottoPurchaseExecutor purchaseExecutor = new LottoPurchaseExecutor(registry, balance);
            purchaseExecutor.execute();

            OutputView.printPurchaseResult(registry.getLottoList());

            Set<LottoNumber> wonNumbers = InputView.getWonNumbers()
                    .stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toSet());

            Lotto winningLotto = new Lotto(wonNumbers);

            LottoDrawExecutor drawExecutor = new LottoDrawExecutor(registry, winningLotto);
            drawExecutor.execute();

            List<DrawResult> results = drawExecutor.getResults();

            OutputView.printDrawResult(results, balance);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
