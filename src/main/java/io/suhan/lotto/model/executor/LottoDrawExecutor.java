package io.suhan.lotto.model.executor;

import io.suhan.lotto.model.DrawResult;
import io.suhan.lotto.model.lotto.Lotto;
import io.suhan.lotto.model.lotto.LottoNumber;
import io.suhan.lotto.model.lotto.LottoRegistry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoDrawExecutor implements LottoExecutor {
    private final LottoRegistry registry;
    private final Lotto winningLotto;
    private final List<DrawResult> results;

    public LottoDrawExecutor(LottoRegistry registry, Lotto winningLotto) {
        this.registry = registry;
        this.winningLotto = winningLotto;
        this.results = new ArrayList<>();
    }

    @Override
    public void execute() {
        for (Lotto lotto : registry.getLottoList()) {
            int matchedCount = calculateMatchedCount(lotto, winningLotto);
            results.add(new DrawResult(lotto, matchedCount));
        }
    }

    public List<DrawResult> getResults() {
        return results;
    }

    private int calculateMatchedCount(Lotto lotto, Lotto winningLotto) {
        Set<LottoNumber> numbers = new HashSet<>(lotto.getNumbers());
        numbers.retainAll(winningLotto.getNumbers());

        return numbers.size();
    }
}
