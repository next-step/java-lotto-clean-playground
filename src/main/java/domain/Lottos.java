package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final int INITIAL_NUMBER = 0;
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos makeLottos(int countPossibleLotto) {
        List<Lotto> lottosList = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < countPossibleLotto; i++) {
            Lotto lotto = Lotto.getLotto();
            lottosList.add(lotto);
        }
        return new Lottos(lottosList);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
