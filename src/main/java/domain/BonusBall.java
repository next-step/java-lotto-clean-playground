package domain;

public class BonusBall {
    private final LottoNumber bonusBall;

    public BonusBall(LottoNumber bonusBall, Lotto winningLotto) {
        validateBonusBallNotDuplicate(bonusBall, winningLotto);
        this.bonusBall = bonusBall;
    }

    private void validateBonusBallNotDuplicate(LottoNumber bonusBall, Lotto winningLotto) {
        if (winningLotto.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 기존 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public static BonusBall of(int number, Lotto winningLotto) {
        return new BonusBall(LottoNumber.of(number), winningLotto);
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }
}
