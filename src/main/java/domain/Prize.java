package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Prize {

    private static final int DECIMAL_PLACES = 2;

    private final BigDecimal amount;

    private Prize(final BigDecimal amount) {
        validateNegativeNumber(amount);
        this.amount = amount.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
    }

    public static Prize from(final long value) {
        return new Prize(BigDecimal.valueOf(value));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void validateNegativeNumber(final BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        }
    }

    public Prize add(final Prize other) {
        return new Prize(this.amount.add(other.amount));
    }

    public Prize multiply(final int count) {
        return new Prize(this.amount.multiply(BigDecimal.valueOf(count)));
    }

    public Prize divideBy(final Prize divisor) {
        validateDenominator(divisor);
        return new Prize(this.amount.divide(divisor.amount, DECIMAL_PLACES, RoundingMode.HALF_UP));
    }

    private void validateDenominator(final Prize divisor) {
        if (divisor.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("분모는 0보다 커야 합니다.");
        }
    }
}
