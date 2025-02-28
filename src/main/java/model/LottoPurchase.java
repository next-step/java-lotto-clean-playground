package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoPurchase {

    private final static int LOTTO_PRICE = 1_000;

    public List<Lotto> purchaseRandomLottos(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int lottoAmount = calculateLottoAmount(purchaseAmount);

        return createRandomLottos(lottoAmount);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 구매 금액은 " + LOTTO_PRICE + "입니다.");
        }

        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액 단위는 " + LOTTO_PRICE + "입니다.");
        }
    }

    private int calculateLottoAmount(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }

    private List<Lotto> createRandomLottos(int lottoAmount) {
        List<Lotto> randomLottos = new ArrayList<>();

        for (int i = 0; i < lottoAmount; i++) {
            randomLottos.add(Lotto.getRandomLotto());
        }

        return Collections.unmodifiableList(randomLottos);
    }

}
