package domain;

public record Money(long amount) {
    private static final long MONEY_UNIT = 1000;
    private static final long MINIMUM_AMOUNT = 0;

    public Money {
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("금액은 0원 이상이어야 합니다.");
        }
        if (amount % MONEY_UNIT != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위로 입력해야 합니다.");
        }
    }

    public Money plus(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money minus(Money other) {
        return new Money(this.amount - other.amount);
    }

    public Money multiplyBy(long multiplier) {
        if (multiplier < 0) {
            throw new IllegalArgumentException("곱셈 값은 음수일 수 없습니다.");
        }
        return new Money(this.amount * multiplier);
    }

    public int divideBy(Money other) {
        if (other.amount == 0) {
            throw new IllegalArgumentException("0원으로 나눌 수 없습니다.");
        }
        return (int) (this.amount / other.amount);
    }

    public long value() {
        return amount;
    }
}
