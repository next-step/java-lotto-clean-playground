package service;

import domain.Lotto;

public class LottoTicketGenerator implements TicketGenerator {

    @Override
    public int generate(int amount) {
        validateNonNegative(amount);
        return amount / Lotto.PRICE;
    }

    private void validateNonNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 음수일 수 없습니다.");
        }
    }
}

