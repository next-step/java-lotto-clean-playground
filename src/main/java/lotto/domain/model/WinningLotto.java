package lotto.domain.model;

public class WinningLotto {

    private final Lotto winningLotto;

    private final LottoNumber bonusNumber;


    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validate(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼 번호는 기존 로또 번호와 중복될 수 없습니다.");
        }
    }
}
