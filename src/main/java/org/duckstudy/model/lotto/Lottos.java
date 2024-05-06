package org.duckstudy.model.lotto;

import java.util.Collections;
import java.util.List;

public class Lottos {

    public final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
