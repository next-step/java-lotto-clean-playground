package domain;

public class LottoTicketGenerator implements TicketGenerator {

    private static final int LOTTO_PRICE = 1000;

    @Override
    public int generate(int amount) {
        return amount / LOTTO_PRICE;
    }
}
