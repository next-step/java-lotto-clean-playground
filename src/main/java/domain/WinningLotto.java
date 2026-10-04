package domain;

import java.util.List;

public class WinningLotto {
    private final Lotto winnerLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        winnerLotto = Lotto.from(numbers);
        validateDuplicate(winnerLotto,bonusNumber);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateDuplicate(Lotto winnerLotto, int bonusNumber) {
        if (winnerLotto.contains(new LottoNumber(bonusNumber))) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank match(Lotto other) {
        return Rank.from(winnerLotto.getCount(other), other.contains(bonusNumber));
    }

}
