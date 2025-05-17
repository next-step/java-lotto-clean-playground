package domain;

import java.util.List;

public record Lottos(List<Lotto> lottos) {
    public Lottos {
        lottos = List.copyOf(lottos);
    }

    public int size() {
        return lottos.size();
    }
}
