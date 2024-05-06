package org.duckstudy.model.lotto;

import java.util.Collections;
import java.util.List;
import org.duckstudy.model.lotto.Lotto;

public class Lottos {

    public final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public List<Lotto> toList() {
        return lottos;
    }
}
