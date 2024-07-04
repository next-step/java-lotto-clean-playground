package domain.lotto;

import domain.common.Money;
import java.util.Arrays;

public enum Prize {
    FIRST(6, new Money(2_000_000_000)),
    SECOND(5, new Money(1_500_000)),
    THIRD(4, new Money(50_000)),
    FOURTH(3, new Money(5_000)),
    LOSING_TICKET(0, Money.ZERO);

    private final Integer match;
    private final Money money;

    Prize(Integer match, Money money) {
        this.match = match;
        this.money = money;
    }

    public static Prize findByMatch(int match) {
        return Arrays.stream(values())
            .filter(prize -> prize.match.equals(match))
            .findFirst()
            .orElseGet(() -> Prize.LOSING_TICKET);
    }

    public Integer getMatch() {
        return match;
    }

    public Money getMoney() {
        return money;
    }
}
