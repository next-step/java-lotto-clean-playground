package domain.lotto;

import domain.lotto.wrap.Money;

public class Payment {

    private final Money amount;
    private final Money price;

    public Payment(Money amount, Money price) {
        validatePurchasable(amount, price);
        this.amount = amount;
        this.price = price;
    }

    public int purchasableCount() {
        return amount.countPurchasable(price);
    }

    public Money paid() {
        return amount.subtract(change());
    }

    public Money change() {
        return amount.change(price);
    }

    public boolean hasChange() {
        return change().getAmount() > 0;
    }

    private void validatePurchasable(Money amount, Money price) {
        if (amount.compareTo(price) < 0) {
            throw new IllegalArgumentException("구입 금액은 로또 가격보다 낮을 수 없습니다. 다시 입력해주세요.\n로또 금액은 " + price.getAmount() + "원입니다.");
        }
    }
}
