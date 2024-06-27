package domain.lotto;

import domain.common.Money;
import java.util.List;

public class LottoResult {

    private static final int LOSING_VALUE = 3;
    private static final int LOTTO_SIZE_LIMIT = 6;
    private final List<Integer> winningNumbers;

    public LottoResult(List<Integer> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validate(List<Integer> matchNumbers) {
        if (matchNumbers.isEmpty()) {
            throw new IllegalArgumentException("빈 값 불가");
        }

        if (matchNumbers.size() < LOTTO_SIZE_LIMIT) {
            throw new IllegalArgumentException("6개 미만 불가");
        }
    }

    public Prize findPrize(LottoTicket lottoTicket) {
        int match = 0;
        for (Integer winningNumber : winningNumbers) {
            if (lottoTicket.contains(winningNumber)) {
                match++;
            }
        }

        if (match < LOSING_VALUE) {
            return Prize.LOSING_TICKET;
        }

        return Prize.findByMatch(match);
    }

    public enum Prize {
        FIRST(6, new Money(2_000_000_000)),
        SECOND(5, new Money(1_500_000)),
        THIRD(4, new Money(50_000)),
        FOURTH(3, new Money(5_000)),
        LOSING_TICKET(null, Money.ZERO);

        private final Integer match;
        private final Money money;

        Prize(Integer match, Money money) {
            this.match = match;
            this.money = money;
        }

        public static Prize findByMatch(int match) {
            for (Prize prize: values()) {
                if (prize.match.equals(match)) {
                    return prize;
                }
            }
            return null;
        }

        public Integer getMatch() {
            return match;
        }

        public Money getMoney() {
            return money;
        }
    }
}
