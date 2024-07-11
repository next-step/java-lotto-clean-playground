package lotto.model;

import java.util.ArrayList;
import java.util.List;

import lotto.utils.generator.Generator;

public class Lottos {

    private List<Lotto> lottos;
    private int customSize;

    public Lottos(int customSize) {
        this.lottos = new ArrayList<>();
        this.customSize = customSize;
    }

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
        this.customSize = lottos.size();
    }

    public static Lottos of(final Generator generator, final int lottoSize) {
        List<Lotto> lottos = new ArrayList<>();
        for (int size = 0; size < lottoSize; size++) {
            lottos.add(Lotto.from(generator));
        }

        return new Lottos(lottos);
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    public void addAll(Lottos lottos) {
        this.lottos.addAll(lottos.getLottos());
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getCustomSize() {
        return customSize;
    }

    public void setCustomSize(int customSize) {
        this.customSize = customSize;
    }
}
