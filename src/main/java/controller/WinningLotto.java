package controller;

import model.Lotto;
import model.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {

    private final Lotto winlotto;
    private final int bonusBall;

    public WinningLotto(Lotto winlotto, int bonusBall) {
        if (winlotto.contain(bonusBall)) {
            throw new IllegalArgumentException("보너스볼을 포함한 로또는 우승할수 없습니다");
        }
        this.winlotto = winlotto;
        this.bonusBall = bonusBall;
    }

    public Rank findRank(Lotto lotto) {
        int matchCount = lotto.countMatch(winlotto);
        boolean bonusMatched = lotto.contain(bonusBall);

        return Rank.of(matchCount, bonusMatched);
    }
}

