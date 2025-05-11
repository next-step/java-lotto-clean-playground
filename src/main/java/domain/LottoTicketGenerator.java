package domain;

public class LottoTicketGenerator implements TicketGenerator {

    private static final int LOTTO_PRICE = 1000;

    @Override
    public int generate(int amount) {
        validateNonNegative(amount);
        return amount / LOTTO_PRICE;
    }

    private void validateNonNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 음수일 수 없습니다.");
        }
    }
}
