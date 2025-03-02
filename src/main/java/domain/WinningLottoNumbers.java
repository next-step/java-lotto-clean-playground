package domain;

public class WinningLottoNumbers {
    private final Lotto winningLotto;
    private final BonusBall bonusBall;

    public WinningLottoNumbers(Lotto winningLotto, int bonusNumber) {
        validateBonusBallNotDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusBall = new BonusBall(LottoNumber.of(bonusNumber), winningLotto);
    }

    private void validateBonusBallNotDuplicate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.contains(LottoNumber.of(bonusNumber))) {
            throw new IllegalArgumentException("보너스 볼은 기존 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public boolean isBonusMatched(Lotto lotto) {
        return lotto.contains(bonusBall.getBonusBall());
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }
}
