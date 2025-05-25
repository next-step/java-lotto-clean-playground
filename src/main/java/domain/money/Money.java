package domain.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(
        BigDecimal amount
) {

    public static final int DIVIDE_SCALE = 2;
    private static final Money ZERO = new Money(BigDecimal.ZERO);

    public static Money from(final String amount) {
        try {
            BigDecimal parsedAmount = new BigDecimal(amount);
            validateNonNegativeAmount(parsedAmount);
            return new Money(parsedAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효한 숫자 형식이 아닙니다.");
        }
    }

    public static Money zero() {
        return ZERO;
    }

    private static void validateNonNegativeAmount(final BigDecimal amount) {
        boolean isNegative = amount.compareTo(BigDecimal.ZERO) < 0;
        if (isNegative) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        }
    }

    public Money add(final Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money multiply(final int multiplier) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)));
    }

    public Money divide(final Money divisor) {
        if (isZero(divisor)) {
            return Money.zero();
        }
        return new Money(this.amount.divide(divisor.amount, DIVIDE_SCALE, RoundingMode.HALF_UP));
    }

    private boolean isZero(final Money divisor) {
        return divisor.amount.compareTo(BigDecimal.ZERO) <= 0;
    }
}
