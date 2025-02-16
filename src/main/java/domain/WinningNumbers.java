package domain;

import java.util.List;

public class WinningNumbers {

    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = new LottoNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public LottoNumbers getWinningNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
