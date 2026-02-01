package lotto.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getValues() {
        return Collections.unmodifiableList(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public Lottos addAll(Lottos other) {
        List<Lotto> combined = new ArrayList<>(this.lottos);
        combined.addAll(other.lottos);
        return new Lottos(combined);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
