package domain;

import java.util.*;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public Lottos(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        List<Lotto> combinedLottos = new ArrayList<>(manualLottos);
        combinedLottos.addAll(autoLottos);
        this.lottos = List.copyOf(combinedLottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
