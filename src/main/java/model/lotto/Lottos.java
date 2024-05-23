package model.lotto;

import model.dice.Dice;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos createWith(final Dice dice, final int lottoSize) {
        List<Lotto> lottos = new ArrayList<>();
        for (int size = 0; size < lottoSize; size++) {
            lottos.add(Lotto.createDefault(dice));
        }
        return new Lottos(lottos);
    }

    public int getLottosSize() {
        return lottos.size();
    }

    public List<List<Integer>> getTotalLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }
}
