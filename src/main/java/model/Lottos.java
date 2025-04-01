package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(PurchaseAmount amount, NumbersGenerator numbersGenerator) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < amount.getAmount(); i++) {
            lottoList.add(Lotto.from(numbersGenerator.generate()));
        }
        return new Lottos(lottoList);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
