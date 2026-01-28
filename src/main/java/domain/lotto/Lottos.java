package domain.lotto;

import java.util.Collections;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getValues() {
        return Collections.unmodifiableList(lottos);
    }

    public int size() {
        return lottos.size();
    }
}
