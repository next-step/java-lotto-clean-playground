package domain;

import java.util.*;

public class WinningNumbers {

    private final Lotto winningNumbers; //로또 당첨 숫자
    private final LottoNumber bonusNumber; //보너스 번호

    public WinningNumbers(Lotto numbers, LottoNumber bonusNumber) {
        validateNoDuplicate(numbers, bonusNumber);
        this.winningNumbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateNoDuplicate(Lotto numbers, LottoNumber bonusNumber) {
        if (numbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
