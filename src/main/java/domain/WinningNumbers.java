package domain;

import java.util.*;

public class WinningNumbers {

    private final Lotto winningNumbers; //로또 당첨 숫자
    private final LottoNumber bonusNumber; //보너스 번호

    public WinningNumbers(Lotto numbers, LottoNumber bonusNumber) {
        this.winningNumbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
