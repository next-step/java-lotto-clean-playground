package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos auto(int autoPurchaseAmount, NumbersGenerator numbersGenerator) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < autoPurchaseAmount; i++) {
            lottoList.add(Lotto.from(numbersGenerator.generate()));
        }
        return new Lottos(lottoList);
    }

    public static Lottos manual(List<Lotto> manualLottos) {
        return new Lottos(manualLottos);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
