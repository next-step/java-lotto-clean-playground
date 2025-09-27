package domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("금액은 0보다 커야 합니다.");
        }
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위여야 합니다.");
        }
    }

    public int getMoney() {
        return money;
    }

    public static LottoTicketCount getTicketCount(Money money) {
        return new LottoTicketCount(money.getMoney() / LOTTO_PRICE);
    }
}
