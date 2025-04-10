package domain;

public class Money {

    private final static int MIN_PRICE = 0;

    private final long price;

    private Money(long price) {
        this.price = price;
    }

    public static Money from(long price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException("구매 금액으로 음수는 입력이 불가능하합니다.");
        }
        return new Money(price);
    }

    public Money add(long addPrice) {
        return Money.from(price + addPrice);
    }

    public Money sub(long subPrice) {
        return Money.from(price - subPrice);
    }

    public long divideBy(long divisor) {
        return price / divisor;
    }

    public long getPrice() {
        return price;
    }

}
