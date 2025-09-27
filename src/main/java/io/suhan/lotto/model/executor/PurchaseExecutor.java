package io.suhan.lotto.model.executor;

import io.suhan.lotto.model.lotto.LottoFactory;
import io.suhan.lotto.model.lotto.LottoRegistry;

public class PurchaseExecutor implements Executor {
    public static final int PRICE_PER_LOTTO = 1000;

    private final LottoRegistry registry;
    private final int balance;

    public PurchaseExecutor(LottoRegistry registry, int balance) {
        this.registry = registry;
        this.balance = balance;
    }

    @Override
    public void execute() {
        int count = getAvailableCount(balance);

        for (int i = 0; i < count; i++) {
            registry.add(LottoFactory.createLotto());
        }
    }

    private int getAvailableCount(int balance) {
        return balance / PRICE_PER_LOTTO;
    }
}
