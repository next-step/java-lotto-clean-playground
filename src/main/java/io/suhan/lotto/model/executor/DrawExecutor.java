package io.suhan.lotto.model.executor;

import io.suhan.lotto.model.DrawResult;
import io.suhan.lotto.model.lotto.Lotto;
import io.suhan.lotto.model.lotto.LottoNumber;
import io.suhan.lotto.model.lotto.LottoRegistry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DrawExecutor implements Executor {
    private final LottoRegistry registry;
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;
    private final List<DrawResult> results;

    public DrawExecutor(LottoRegistry registry, Lotto winningLotto, LottoNumber bonusNumber) {
        this.registry = registry;
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
        this.results = new ArrayList<>();
    }

    @Override
    public void execute() {
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        for (Lotto lotto : registry.getLottos()) {
            int matchedCount = calculateMatchedCount(lotto, winningLotto);
            boolean bonusMatched = isBonusMatched(matchedCount, lotto);
            results.add(DrawResult.of(matchedCount, bonusMatched));
        }
    }

    private int calculateMatchedCount(Lotto lotto, Lotto winningLotto) {
        Set<LottoNumber> numbers = new HashSet<>(lotto.getNumbers());
        numbers.retainAll(winningLotto.getNumbers());

        return numbers.size();
    }

    private boolean isBonusMatched(int matchedCount, Lotto lotto) {
        if (matchedCount != Lotto.LOTTO_SIZE - 1) {
            return false;
        }

        return lotto.getNumbers().contains(bonusNumber);
    }

    public List<DrawResult> getResults() {
        return results;
    }
}
