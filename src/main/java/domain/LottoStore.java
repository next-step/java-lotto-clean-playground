package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
    private static final Money LOTTO_PRICE = new Money(1000);
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public Lottos buy(Money money) {
        validatePrice(money);
        int count = calculateLottoCount(money);
        return generateLottos(count);
    }

    private int calculateLottoCount(Money money) {
        return money.divideBy(LOTTO_PRICE);
    }

    private void validatePrice(Money money) {
        if (money.isLessThan(LOTTO_PRICE)) {
            throw new IllegalArgumentException("구매 금액은 1000원 이상이어야 합니다.");
        }

        if (!money.isDivisibleBy(LOTTO_PRICE)) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위여야 합니다.");
        }
    }

    private Lottos generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoGenerator.generateLotto());
        }
        return new Lottos(lottos);
    }
}
