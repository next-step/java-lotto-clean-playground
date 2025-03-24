package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos createLottos(int amount, NumbersGenerator numbersGenerator) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottoList.add(new Lotto(numbersGenerator));
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
