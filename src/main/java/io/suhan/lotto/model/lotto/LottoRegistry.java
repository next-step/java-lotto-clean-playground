package io.suhan.lotto.model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRegistry {
    private final List<Lotto> lottos;

    public LottoRegistry() {
        this.lottos = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
