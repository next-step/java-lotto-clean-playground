package domain;

import constant.LottoConstants;

import java.util.Objects;

public class TicketCount {
    private int value;

    public TicketCount(final int value) {
        validateNumberLowerBound(value);
        this.value = value;
    }

    public void subtractTicketCount(final TicketCount ticketCount) {
        int substractResult = value - ticketCount.getValue();
        if (substractResult < 0) {
            throw new IllegalArgumentException("차감 가능한 티켓의 갯수를 초과했습니다.");
        }
        this.value = substractResult;
    }

    private void validateNumberLowerBound(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("티켓의 갯수는 0 또는 음수가 될 수 없습니다.");
        }
    }

    public static TicketCount from(final Amount amount) {
        return new TicketCount(amount.getValue() / LottoConstants.LOTTO_PRICES_UNIT);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketCount that)) return false;
        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
