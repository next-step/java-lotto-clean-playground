package io.suhan.lotto.model.executor;

import io.suhan.lotto.model.lotto.Lotto;
import io.suhan.lotto.model.lotto.LottoFactory;
import io.suhan.lotto.model.lotto.LottoRegistry;
import io.suhan.lotto.model.lotto.LottoType;
import io.suhan.lotto.view.InputView;
import java.util.List;
import java.util.Set;

public class PurchaseExecutor implements Executor {
    public static final int PRICE_PER_LOTTO = 1000;

    private final LottoRegistry registry;
    private int balance;
    private final int manualCount;

    public PurchaseExecutor(LottoRegistry registry, int balance, int manualCount) {
        this.registry = registry;
        this.balance = balance;
        this.manualCount = manualCount;
    }

    @Override
    public void execute() {
        if (manualCount > 0) {
            purchaseManualNumbers();
        }

        int autoCount = getAvailableCount(balance);

        for (int i = 0; i < autoCount; i++) {
            registry.add(LottoFactory.createLotto(LottoType.AUTOMATIC));
        }
    }

    private void purchaseManualNumbers() {
        List<Set<Integer>> manualNumbersList = InputView.getValidManualNumbers(manualCount);

        for (Set<Integer> numbers : manualNumbersList) {
            Lotto lotto = Lotto.of(LottoType.MANUAL, LottoFactory.toLottoNumbers(numbers));

            registry.add(lotto);
            balance -= PRICE_PER_LOTTO;
        }
    }

    private int getAvailableCount(int balance) {
        return balance / PRICE_PER_LOTTO;
    }
}
