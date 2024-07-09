package lotto.model;

import java.util.ArrayList;
import java.util.List;

import lotto.utils.generator.Generator;

public class Lottos {

    private List<Lotto> lottos;
    private List<Lotto> customLottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
        this.customLottos = new ArrayList<>();
    }

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
        this.customLottos = new ArrayList<>();
    }

    public static Lottos of(final Generator generator, final int lottoSize) {
        List<Lotto> lottos = new ArrayList<>();
        for (int size = 0; size < lottoSize; size++) {
            lottos.add(Lotto.from(generator));
        }

        return new Lottos(lottos);
    }

    public void addCustom(Lotto lotto) {
        customLottos.add(lotto);
    }

    public void addAllCustom(Lottos lottos) {
        customLottos.addAll(lottos.getCustomLottos());
    }

    public int size() {
        return lottos.size() + customLottos.size();
    }

    public int autoSize() {
        return lottos.size();
    }

    public int customSize() {
        return customLottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<Lotto> getCustomLottos() {
        return customLottos;
    }
}
