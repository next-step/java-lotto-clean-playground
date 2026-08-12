package domain;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusBall;

    private WinningLotto(Lotto lotto, LottoNumber bonusBall) {
        validate(lotto, bonusBall);
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    public static WinningLotto from(Lotto lotto, LottoNumber bonusBall) {
        return new WinningLotto(lotto, bonusBall);
    }

    private static void validate(Lotto lotto, LottoNumber bonusBall) {
        if (lotto.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank findRank(Lotto lotto) {
        int count = lotto.countLotto(this.lotto);
        boolean matchBonus = lotto.contains(bonusBall);

        return Rank.find(count, matchBonus);
    }
}
