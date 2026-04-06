package lotto.domain;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank judge(Lotto userLotto) {
        int matchCount = userLotto.countMatch(winningLotto);
        boolean matchBonus = userLotto.contains(bonusNumber);
        return Rank.valueOf(matchCount, matchBonus);
    }
}
