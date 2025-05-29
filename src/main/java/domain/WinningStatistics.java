package domain;

import java.util.Map;

public class WinningStatistics {
    private final Map<Prize, Integer> prizeCounts;
    private final int moneySpent;

    //로또 하나마다 당첨번호와 일치하는 개수를 담은 리스트를 enum 으로 리턴,prize별 개수 세기
    public WinningStatistics(Map<Prize, Integer> prizeCounts, int moneySpent) {
        this.prizeCounts = prizeCounts;
        this.moneySpent = moneySpent;
    }

    //등수별 당첨 횟수 X 당첨 금액 = 개별 수익 다 더하기
    public int totalReward() {
        return
                prizeCounts.entrySet().stream()
                        .mapToInt(e -> e.getKey().getReward() * e.getValue())
                        .sum();
    }

    //수익률 계산
    public double calculateRate() {
        if (moneySpent == 0) return 0.0;
        return (double) totalReward() / moneySpent;
    }

    public Map<Prize, Integer> getPrizeCounts() {
        return prizeCounts;
    }
}
