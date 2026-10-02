package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복일 수 없다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return winningNumbers.contains(number);
    }

    public boolean matchesBonusNumber(LottoNumber number) {
        return bonusNumber.equals(number);
    }

}
