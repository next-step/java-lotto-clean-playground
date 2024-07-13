package domain.lotto;

import domain.common.Money;
import java.util.Arrays;

public enum Prize {
    FIRST(6, false, new Money(2_000_000_000)),
    SECOND_BONUS_BALL(5, true, new Money(30_000_000)),
    SECOND(5, false, new Money(1_500_000)),
    THIRD(4, false, new Money(50_000)),
    FOURTH(3, false, new Money(5_000)),
    LOSING_TICKET(0, false, Money.ZERO);

    private final Integer match;
    private final boolean bonusNumber;
    private final Money money;

    Prize(Integer match, boolean bonusNumber, Money money) {
        this.match = match;
        this.bonusNumber = bonusNumber;
        this.money = money;
    }

    public static Prize findByMatch(int match, boolean hasBonusNumber) {
        return Arrays.stream(values())
            .filter(prize -> prize.match.equals(match) && prize.bonusNumber == hasBonusNumber)
            .findFirst()
            .orElse(Prize.LOSING_TICKET);
    }

    public Integer getMatch() {
        return match;
    }

    public Money getMoney() {
        return money;
    }

    public boolean hasBonusNumber() {
        return bonusNumber;
    }
}
