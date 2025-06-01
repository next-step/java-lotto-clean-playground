package domain;

import java.util.Objects;

public class Prize {
    private final long amount;

    private Prize(long amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Prize from(long amount) {
        return new Prize(amount);
    }

    private void validate(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("상금은 음수일 수 없습니다.");
        }
    }

    public Prize add(Prize other) {
        return new Prize(this.amount + other.amount);
    }

    public Prize multiply(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("개수는 음수일 수 없습니다.");
        }
        return new Prize(this.amount * count);
    }

    public long getAmount() {
        return amount;
    }

    public static double calculateRateOfReturn(Prize totalPrize, int paidMoney) {
        return (double) totalPrize.getAmount() / paidMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Prize prize)) {
            return false;
        }
        return amount == prize.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
