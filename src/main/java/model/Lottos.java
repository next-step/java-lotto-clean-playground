package model;

import model.lotto.AutoLotto;
import model.lotto.Lotto;
import model.lotto.ManualLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(List<ManualLotto> manualLottos, int autoPurchaseAmount, NumbersGenerator numbersGenerator) {
        List<Lotto> lottoList = new ArrayList<>(manualLottos);
        for (int i = 0; i < autoPurchaseAmount; i++) {
            lottoList.add(AutoLotto.of(numbersGenerator.generate()));
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
