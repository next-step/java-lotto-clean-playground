package domain;

import java.util.List;

public record Lottos(List<Lotto> lottos) {

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
