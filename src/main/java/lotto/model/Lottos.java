package lotto.model;

import java.util.ArrayList;
import java.util.List;

import lotto.utils.generator.NumberGenerator;

public class Lottos {

    private List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(final NumberGenerator generator, final int lottoSize) {
        List<Lotto> lottos = new ArrayList<>();
        for (int size = 0; size < lottoSize; size++) {
            lottos.add(Lotto.from(generator));
        }

        return new Lottos(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
