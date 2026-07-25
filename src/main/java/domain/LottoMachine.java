package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Lottos buy(Money money) {
        validateMoney(money);
        int count = money.divide(LOTTO_PRICE);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generator.generate()));
        }
        return new Lottos(lottos);
    }

    private void validateMoney(Money money) {
        if (!money.isDivisibleBy(LOTTO_PRICE)) {
            throw new IllegalArgumentException("구입금액은 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }
}
