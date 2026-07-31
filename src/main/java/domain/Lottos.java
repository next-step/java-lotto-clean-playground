package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos = new ArrayList<>();

    private int lottoCount;

    public Lottos(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 구입 금액은 1000원 입니다.");
        }

        calculateLottoCount(amount);

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto());
        }
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    public int getLottoCount() {
        return lottoCount;
    }

    private void calculateLottoCount(int amount) {
        lottoCount = amount / LOTTO_PRICE;
    }
}
