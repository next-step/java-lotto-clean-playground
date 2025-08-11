package model;

import java.util.List;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lotto;

    public Lottos(List<Lotto> lotto) {
        this.lotto = lotto;
    }

    public List<Lotto> getLotto() {
        return lotto;
    }

    public Stream<Lotto> stream() {
        return lotto.stream();
    }

    public int size() {
        return lotto.size();
    }

    public List<Lotto> asList() {
        return lotto;
    }
}
