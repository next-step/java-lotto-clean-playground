package domain;

import java.util.EnumMap;
import java.util.Map;

public class Statistics {

    private final Map<Prize, Integer> matchCounts = new EnumMap<>(Prize.class);

    public Map<Prize, Integer> getMatchCounts() {
        return matchCounts;
    }

    public Statistics() {
        for (Prize prize : Prize.values()) {
            matchCounts.put(prize, 0);
        }
    }

    //(일반 번호)맞은 개수 업데이트하는 메서드
    public void matchCountUpdate(int matchCount, boolean isBonusMatch) {
        if (matchCount == 6) {
            countUp(Prize.SIX_MATCHES);
        } else if (matchCount == 5 && isBonusMatch) {
            countUp(Prize.FIVE_MATCHES_BONUS);
        } else if (matchCount == 5) {
            countUp(Prize.FIVE_MATCHES);
        } else if (matchCount == 4) {
            countUp(Prize.FOUR_MATCHES);
        } else if (matchCount == 3) {
            countUp(Prize.THREE_MATCHES);
        }
    }

    private void countUp(Prize prize) {
        matchCounts.put(prize, matchCounts.get(prize) + 1);
    }

    //수익률 계산 메서드
    public double calculateReturnRate(int totalSpent) {
        int totalEarnings = 0;
<<<<<<< HEAD
        for (Prize prize : Prize.values()) {
            totalEarnings += matchCounts.getOrDefault(prize, 0) * prize.getPrizeAmount();
=======
        for (Prize prize: Prize.values()){
            totalEarnings += matchCounts.getOrDefault(prize,0)* prize.getPrizeAmount();
>>>>>>> 3c1981496e2da5ad9d55b394c292cecd8ff7ceaf
        }
        return (double) totalEarnings / totalSpent;
    }
}
