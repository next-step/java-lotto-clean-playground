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
            throw new IllegalArgumentException();
        }
    }

    public Rank findRank(Lotto lotto) {
        int count = lotto.countLotto(lotto);
        boolean matchBonus = lotto.contains(bonusBall);

        return Rank.find(count, matchBonus);
    }

    public Lotto getWinningLotto() {
        return lotto;
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }
}
