package domain;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private static final int PRIZE_3_MATCHES = 5000;
    private static final int PRIZE_4_MATCHES = 50000;
    private static final int PRIZE_5_MATCHES = 1500000;
    private static final int PRIZE_6_MATCHES = 2000000000;

    private final Map<Integer, Integer> matchCounts;
    public Statistics(){
        matchCounts = new HashMap<>();
        matchCounts.put(3, 0);
        matchCounts.put(4, 0);
        matchCounts.put(5, 0);
        matchCounts.put(6, 0);
    }

    public Map<Integer, Integer> getMatchCounts() {
        return matchCounts;
    }

    //맞은 개수 업데이트하는 메서드
    public void updateStatistics(int matchingCount){
        if (matchCounts.containsKey(matchingCount)){
            matchCounts.put(matchingCount, matchCounts.get(matchingCount)+1);
        }
    }

    //수익률 계산 메서드
    public double calculateReturnRate(int totalSpent){
        int totalEarnings = (matchCounts.get(3) * PRIZE_3_MATCHES) +
                (matchCounts.get(4) * PRIZE_4_MATCHES) +
                (matchCounts.get(5) * PRIZE_5_MATCHES) +
                (matchCounts.get(6) * PRIZE_6_MATCHES);
        return (double) totalEarnings / totalSpent;
    }

    public int getPrize3() {
        return PRIZE_3_MATCHES;
    }

    public int getPrize4() {
        return PRIZE_4_MATCHES;
    }

    public int getPrize5() {
        return PRIZE_5_MATCHES;
    }

    public int getPrize6() {
        return PRIZE_6_MATCHES;
    }
}

