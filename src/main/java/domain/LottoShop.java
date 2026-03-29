package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {
    private static final int LOTTO_PRICE = 1000;
    private final NumberGenerator numberGenerator;

    public LottoShop(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos purchase(int amount) {
        validateAmount(amount);
        return new Lottos(createLottos(amount));
    }

    private List<Lotto> createLottos(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < calculateCount(amount); i++) {
            lottos.add(new Lotto(numberGenerator.generator()));
        }
        return lottos;
    }

    private int calculateCount(int amount) {
        return amount / LOTTO_PRICE;
    }

    private void validateAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 금액은 1000원 이상이어야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
