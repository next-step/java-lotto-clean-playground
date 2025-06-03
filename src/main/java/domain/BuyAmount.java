package domain;

import java.util.Objects;

public class BuyAmount {
    private static final int TICKET_PRICE = 1000;

    private final int amount;
    private final int handCount;

    public BuyAmount(int amount, int handCount) {
        validatePrice(amount, handCount);
        this.amount = amount;
        this.handCount = handCount;
    }

    private void validatePrice(int amount, int handCount) {
        if (amount < TICKET_PRICE) {
            throw new IllegalArgumentException("금액은 1000원 이상이어야 합니다.");
        }
        int totalTickets = amount / TICKET_PRICE;
        if (handCount > totalTickets) {
            throw new IllegalArgumentException("수동으로 구매하려는 횟수가 구입 금액을 초과하였습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BuyAmount buyAmount)) {
            return false;
        }
        return amount == buyAmount.amount && handCount == buyAmount.handCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, handCount);
    }

    public int getTotalCount() {
        return amount / TICKET_PRICE;
    }

    public int getManualCount() {
        return handCount;
    }

    public int getAutoCount() {
        return getTotalCount() - handCount;
    }

    public int getAmount() {
        return amount;
    }
}
