package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> values;

    public Lottos(List<Lotto> values) {
        this.values = Collections.unmodifiableList(
                new ArrayList<>(values)
        );
    }

    public WinningResult calculateResult(
            WinningLotto winningLotto
    ) {
        WinningResult winningResult = new WinningResult();

        values.stream()
                .map(winningLotto::determineRank)
                .forEach(winningResult::add);

        return winningResult;
    }

    public List<Lotto> getValues() {
        return new ArrayList<>(values);
    }

    public int size() {
        return values.size();
    }
}
