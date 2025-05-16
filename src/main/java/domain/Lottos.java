package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int count() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public static Lottos merge(Lottos manual, Lottos auto) {
        List<Lotto> combined = new ArrayList<>(manual.getLottos());
        combined.addAll(auto.getLottos());
        return new Lottos(combined);
    }
}
