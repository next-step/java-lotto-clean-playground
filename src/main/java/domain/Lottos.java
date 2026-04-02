package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final int PRICE = 1000;

    private final List<Lotto> lottos;

    public Lottos(final int purchaseAmount) {
        this.lottos = generateLottos(purchaseAmount / PRICE);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto());
        }
        return lottos;
    }
}
