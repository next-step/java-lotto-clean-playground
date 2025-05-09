package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static domain.LottoShop.PRICE_PER_TICKET;

public class Money {

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money from(int inputAmount) {
        BigDecimal decimalAmount = BigDecimal.valueOf(inputAmount);
        validate(decimalAmount);
        return new Money(decimalAmount);
    }

    public int getPurchasedLottoCount() {
        return amount.divide(PRICE_PER_TICKET, RoundingMode.HALF_UP).intValue();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private static void validate(BigDecimal value) {
        if (value.compareTo(PRICE_PER_TICKET) < 0) {
            throw new IllegalArgumentException("로또는 최소 1장 이상 구매해야 합니다.");
        }

        if (value.remainder(PRICE_PER_TICKET).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("1000원 단위로만 구매할 수 있습니다.");
        }
    }
}
