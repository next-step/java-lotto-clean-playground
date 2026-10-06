package domain.lotto;

import domain.result.MatchResult;

import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        Lotto winningLotto = Lotto.from(winningNumbers);
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        validateDuplicateBonusNumber(winningLotto, bonusLottoNumber);

        this.winningNumbers = winningLotto;
        this.bonusNumber = bonusLottoNumber;
    }

    private void validateDuplicateBonusNumber(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(
                    "보너스 볼은 당첨 번호와 중복될 수 없습니다."
            );
        }
    }

    public MatchResult createMatchResult(Lotto lottoNumbers) {
        int count = lottoNumbers.calculateMatchCount(winningNumbers);
        boolean bonusMatched = lottoNumbers.contains(bonusNumber);

        return new MatchResult(count, bonusMatched);
    }
}
