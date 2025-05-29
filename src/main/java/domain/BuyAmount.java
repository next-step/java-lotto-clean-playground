package domain;

import java.util.Objects;

public class BuyAmount {
    private static final int TICKET_PRICE = 1000;
    private final int amount;

    public BuyAmount(int amount) {
        validatePrice(amount);
        this.amount = amount;
    }

    private void validatePrice(int amount) {
        if (amount < TICKET_PRICE) {
            throw new IllegalArgumentException("금액은 1000원 이상이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BuyAmount buyAmount)) {
            return false;
        }
        return amount == buyAmount.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    public int getPurchasableTicketCount() {
        return amount / TICKET_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}
