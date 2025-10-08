package domain.value;

public record Money(int value) {
    public Money {
        if (value <= 0) throw new IllegalArgumentException("구매 금액은 양수여야 합니다.");
    }

    public int calculateTicketCount(int pricePerTicket) {
        return value / pricePerTicket;
    }
}
