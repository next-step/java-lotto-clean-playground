package domain;

public class LottoDrawResult {

    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoDrawResult(LottoNumbers winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public LottoNumbers getWinningNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
