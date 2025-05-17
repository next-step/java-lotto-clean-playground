package domain;

import exception.BonusNumberDuplicationException;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;
        validate(lotto, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.getNumbers().lottoNumbers().stream()
                .anyMatch(n -> n.equals(bonusNumber))) {
            throw new BonusNumberDuplicationException("보너스볼 번호가 메인 번호와 중복됩니다.");
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public boolean isMatchBonusNumber(Lotto lotto) {
        return lotto.getNumbers().lottoNumbers().stream()
                .anyMatch(n -> n.equals(bonusNumber));
    }
}
