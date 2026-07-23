package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
    private static final int LOTTO_PRICE = 1000;
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public List<Lotto> buy(int price) {
        validatePrice(price);
        int count = calculateLottoCount(price);
        return generateLottos(count);
    }

    private void validatePrice(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException("구매 금액은 1000원 이상이어야 합니다.");
        }

        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위여야 합니다.");
        }
    }

    private int calculateLottoCount(int price) {
        return price / LOTTO_PRICE;
    }

    private List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoGenerator.generateLotto());
        }
        return lottos;
    }
}
