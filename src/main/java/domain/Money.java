package domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < 1000) {
            throw new IllegalArgumentException("로또 구매를 위해서는 금액은 1000원 보다 커야 합니다.");
        }
    }

    public int getMoney() {
        return money;
    }

    public LottoTicketCount getTicketCount() {
        return new LottoTicketCount(this.money / LOTTO_PRICE);
    }

}
