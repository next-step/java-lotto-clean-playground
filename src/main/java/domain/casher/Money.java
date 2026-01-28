package domain.casher;

public class Money {

    private static final int LOTTO_PRICE = 1_000;
    private final int amount;

    public Money(String input) {
        this.amount = parse(input);
    }

    private int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

}
