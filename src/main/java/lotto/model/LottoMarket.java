package lotto.model;

import static lotto.global.Constants.LOTTO_COST;

import lotto.utils.generator.Generator;

public class LottoMarket {
    private final Generator generator;

    public LottoMarket(final Generator generator) {
        this.generator = generator;
    }

    public Lottos getLottos(final Money money, Lottos customLottos) {
        validateDivideZero(money.getMoney());

        int customMoney = customLottos.getCustomSize() * LOTTO_COST;
        int lottoSize = getLottoSize(money.getMoney() - customMoney, LOTTO_COST);

        Lottos lottos = createLottos(lottoSize);
        lottos.setCustomSize(customLottos.getCustomSize());

        lottos.addAll(customLottos);

        return lottos;
    }

    private Lottos createLottos(final int lottoSize) {
        return Lottos.of(generator, lottoSize);
    }

    private static void validateDivideZero(final int money) {
        if (money % LOTTO_COST != 0) {
            throw new IllegalArgumentException("돈은 " + LOTTO_COST + "의 배수여야 함");
        }
    }

    public int getLottoSize(final int money, final int cost) {
        return money / cost;
    }
}
