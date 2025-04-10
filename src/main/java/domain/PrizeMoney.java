package domain;

import java.math.BigDecimal;

public class PrizeMoney {

    private final BigDecimal amount;

    public PrizeMoney(BigDecimal amount) {
        validate(amount);
        this.amount = amount;
    }

    public PrizeMoney plus(PrizeMoney other) {
        return new PrizeMoney(this.amount.add(other.amount));
    }

    public PrizeMoney multiply(int count) {
        return new PrizeMoney(this.amount.multiply(BigDecimal.valueOf(count)));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void validate(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("상금은 0보다 작을 수 없습니다");
        }
    }
}
