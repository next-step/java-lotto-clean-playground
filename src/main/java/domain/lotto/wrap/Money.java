package domain.lotto.wrap;

public class Money {

    private final int amount;

    public Money(int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("소지금은 음수일 수 없습니다.");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Money plus(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money subtract(Money other) {
        return  new Money(amount - other.amount);
    }

    public Money multiply(int count) {
        return new Money(amount * count);
    }

    // 몇 개 구매할 수 있음을 반환하는 함수
    public int countPurchasable(Money price) {
        return amount / price.amount;
    }

    // 구매하고 남는 잔돈을 반환하는 함수
    public Money change(Money price) {
        return new Money(amount % price.amount);
    }
}
