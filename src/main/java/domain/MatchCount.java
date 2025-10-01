package domain;

import java.util.EnumMap;
import java.util.List;

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

    public static MatchCount calculateStatistics(List<Lotto> tickets, Lotto answer, LottoNumber bonusBall) {
        MatchCount matchCount = new MatchCount();

        for (Lotto lotto : tickets) {
            int match = Match.getMatchCount(lotto, answer);

            boolean hasBonusBall = (lotto.contains(bonusBall) == 1);

            LottoPrice price = determineLottoPrice(match, hasBonusBall);

            if (price != null) {
                matchCount.addCount(price, 1);
            }
        }
        return matchCount;
    }

    public static LottoPrice determineLottoPrice(int match, boolean hasBonus) {
        if (match == 6) {
            return LottoPrice.MATCH_6;
        }
        if (match == 5) {
            if (hasBonus) {
                return LottoPrice.MATCH_5_BONUS;
            }
            return LottoPrice.MATCH_5;
        }

        if (match == 4) {
            return LottoPrice.MATCH_4;
        }
        if (match == 3) {
            return LottoPrice.MATCH_3;
        }
        return null;
    }
}

