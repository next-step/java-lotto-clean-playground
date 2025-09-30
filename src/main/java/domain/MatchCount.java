package domain;

import java.util.EnumMap;
import java.util.List;
import domain.Lotto;

public class MatchCount {
    private final EnumMap<LottoPrice, Integer> counts = new EnumMap<>(LottoPrice.class);

    public MatchCount() {
        for (LottoPrice price : LottoPrice.values()) {
            counts.put(price, 0);
        }
    }

    public void addCount(LottoPrice price, int count) {
        counts.put(price, counts.get(price) + count);
    }

    public int getCount(LottoPrice price) {
        return counts.get(price);
    }

    public static MatchCount countAllMatches(List<Lotto> tickets, Lotto answer) {
        MatchCount matchCount = new MatchCount();
        for (Lotto lotto : tickets) {
            int match = Match.getMatchCount(lotto, answer);
            try {
                LottoPrice price = LottoPrice.valueOf("MATCH_" + match);
                matchCount.addCount(price, 1);
            } catch (IllegalArgumentException e) {
            }
        }
        return matchCount;
    }
    public static MatchCount countBonusBallMatches(List<Lotto> tickets, Lotto answer, LottoNumber bonusBall) {
        MatchCount matchCount = new MatchCount();
        for (Lotto lotto : tickets) {
            int match = Match.getMatchCount(lotto, answer);
            if (match == 5 && lotto.contains(bonusBall)==1) {
                matchCount.addCount(LottoPrice.MATCH_5_BONUS, 1);
            }
        }
        return matchCount;
    }

    public void merge(MatchCount bonusCount) {
        for (LottoPrice price : LottoPrice.values()) {
            this.addCount(price, bonusCount.getCount(price));
        }
    }
}
