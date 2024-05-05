package model;

import static model.Rank._2ND_PRIZE;

import java.util.List;

public record WinningLotto(Lotto lotto, LottoNumber bonusNumber) {

    public WinningLotto {
        validate(lotto, bonusNumber);
    }

    public static WinningLotto from(List<Integer> numbers, int bonusNumber) {
        return new WinningLotto(Lotto.from(numbers), new LottoNumber(bonusNumber));
    }

    private void validate(Lotto lotto, LottoNumber bonusNumber) {
        validateDuplication(lotto, bonusNumber);
    }

    private void validateDuplication(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank match(Lotto other) {
        int matchCount = (int) other.numbers().stream()
                                    .filter(lotto.numbers()::contains)
                                    .count();
        if (matchCount == _2ND_PRIZE.matchCount()) {
            return Rank.of(matchCount, other.numbers().contains(bonusNumber));
        }
        return Rank.of(matchCount, false);
    }
}
