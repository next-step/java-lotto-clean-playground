package domain.lotto;

import domain.enums.LotteryPrize;
import domain.lotto.wrap.LottoNumber;

public class WinningLotto {

    private final Lotto numbers;
    private final LottoNumber bonus;

    public WinningLotto(Lotto numbers, LottoNumber bonus) {

        validateBonus(numbers, bonus);

        this.numbers = numbers;
        this.bonus = bonus;
    }

    public LotteryPrize match(Lotto ticket) {
        return LotteryPrize.of(numbers.countMatch(ticket), ticket.contains(bonus));
    }

    private void validateBonus(Lotto numbers, LottoNumber bonus) {
        if (numbers.contains(bonus)) {
            throw new IllegalArgumentException("추첨된 로또 번호는 서로 중복될 수 없습니다.");
        }
    }
}
