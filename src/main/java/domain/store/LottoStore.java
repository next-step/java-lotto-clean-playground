package domain.store;

import domain.lotto.Lottos;
import domain.money.Money;
import java.math.BigDecimal;
import strategy.LottoNumberGenerator;

public class LottoStore {

    public static final Money LOTTO_PRICE = new Money("1000");

    private final LottoNumberGenerator generator;

    public LottoStore(final LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Lottos buy(final Money purchaseAmount) {
        validateMinimum(purchaseAmount);
        validateUnit(purchaseAmount);
        int count = calculateLottoCount(purchaseAmount);
        return lottoMachine(count);
    }

    private void validateUnit(final Money purchaseAmount) {
        boolean isInvalidUnit =
                purchaseAmount.getAmount().remainder(LOTTO_PRICE.getAmount()).compareTo(BigDecimal.ZERO) != 0;
        if (isInvalidUnit) {
            throw new IllegalArgumentException("구입 금액은 %s원 단위로 입력해야 합니다.".formatted(LOTTO_PRICE.getAmount()));
        }
    }

    private void validateMinimum(final Money purchaseAmount) {
        boolean isBelowMinimum = purchaseAmount.getAmount().compareTo(LOTTO_PRICE.getAmount()) < 0;
        if (isBelowMinimum) {
            throw new IllegalArgumentException("최소 %s원 이상 입력해야 합니다.".formatted(LOTTO_PRICE.getAmount()));
        }
    }

    private Lottos lottoMachine(final int count) {
        return Lottos.generate(count, generator);
    }

    private int calculateLottoCount(final Money purchaseAmount) {
        return purchaseAmount.divideBy(LOTTO_PRICE).getAmount().intValueExact();
    }
}
