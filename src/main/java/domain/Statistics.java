package domain;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private final Map<Prize, Integer> matchCounts = new HashMap<>();

    public Map<Prize, Integer> getMatchCounts() {
        return matchCounts;
    }
    public Statistics(){
        for (Prize prize : Prize.values()){
            matchCounts.put(prize,0);
        }
    }

    //(일반 번호)맞은 개수 업데이트하는 메서드
    public void updateStatistics(int matchCount, boolean isBonusMatch){
        if (matchCount == 6){
            CountUp(Prize.SIX_MATCHES);
        } else if (matchCount == 5 && isBonusMatch){
            CountUp(Prize.FIVE_MATCHES_BONUS);
        } else if (matchCount == 5){
            CountUp(Prize.FIVE_MATCHES);
        } else if (matchCount == 4){
            CountUp(Prize.FOUR_MATCHES);
        } else if (matchCount == 3){
            CountUp(Prize.THREE_MATCHES);
        }
    }

    private void CountUp(Prize prize){
        matchCounts.put(prize, matchCounts.get(prize)+1);
    }

    //수익률 계산 메서드
    public double calculateReturnRate(int totalSpent){
        int totalEarnings = 0;
        for (Prize prize: Prize.values()){
            totalEarnings += matchCounts.getOrDefault(prize,0)* prize.getPrizeAmount();
        }
        return (double) totalEarnings / totalSpent;
    }
}
