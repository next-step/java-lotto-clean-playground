package domain;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    public static int calculateTicketCount(Money money) {
        int amount = money.getAmount();
        validateLottoUnit(amount);
        return amount / LOTTO_PRICE;
    }

    private static void validateLottoUnit(int amount) {
        if (amount < LOTTO_PRICE || amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("로또는 1000원 단위로만 구매 가능합니다.");
        }
    }
}
