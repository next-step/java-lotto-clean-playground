package domain.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private static final int DECIMAL_PLACES = 2;

    private final BigDecimal amount;

    public Money(final String value) {
        parseAndValidate(value);
        this.amount = new BigDecimal(value);
    }

    public static Money zero() {
        return new Money("0");
    }

    private void parseAndValidate(final String value) {
        try {
            BigDecimal parsed = new BigDecimal(value);
            validateNonNegativeAmount(parsed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효한 숫자 형식이 아닙니다.");
        }
    }

    private void validateNonNegativeAmount(final BigDecimal amount) {
        boolean isNegative = amount.compareTo(BigDecimal.ZERO) < 0;
        if (isNegative) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        }
    }

    public Money add(final Money other) {
        return new Money(this.amount.add(other.amount).toPlainString());
    }

    public Money multiply(final int multiplier) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)).toPlainString());
    }

    public Money divideBy(final Money divisor) {
        validateNonZeroDivisor(divisor);
        return new Money(this.amount.divide(divisor.amount, DECIMAL_PLACES, RoundingMode.HALF_UP).toPlainString());
    }

    private void validateNonZeroDivisor(final Money divisor) {
        boolean isZeroOrNegative = divisor.amount.compareTo(BigDecimal.ZERO) <= 0;
        if (isZeroOrNegative) {
            throw new IllegalArgumentException("분모는 0보다 커야 합니다.");
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return amount.toPlainString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money money)) {
            return false;
        }
        return amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
