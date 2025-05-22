package domain;

public class WinningLotto {
    public static final String ERROR_DUPLICATE_BONUS = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        validateBonusNumber(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
    }

    public int countMatch(Lotto other) {
        return winningLotto.countMatch(other);
    }

    public boolean matchBonus(Lotto other) {
        return other.contains(bonusNumber);
    }
}
