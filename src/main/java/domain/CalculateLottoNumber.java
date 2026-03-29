package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CalculateLottoNumber {
    private final Map<Rank, Integer> matchResults;

    public CalculateLottoNumber(LottoNumber lottoNumber, List<Integer> winningNumbers) {
        this.matchResults = new EnumMap<>(Rank.class);
        initResults();
        calculate(lottoNumber.getLottoNumber(), winningNumbers);
    }

    private void initResults() {
        for (Rank rank : Rank.values()) {
            matchResults.put(rank, 0);
        }
    }

    private void calculate(List<Lotto> lottoNumber, List<Integer> winningNumbers) {
        for (Lotto lotto : lottoNumber) {
            int matchCount = countMatch(lotto.getNumbers(), winningNumbers);
            Rank rank = Rank.valueOfMatchCount(matchCount);
            matchResults.put(rank, matchResults.get(rank) + 1);
        }
    }

    private int countMatch(List<Integer> lottoNumber, List<Integer> winningNumbers) {
        int count = 0;
        for (Integer number : lottoNumber) {
            if (winningNumbers.contains(number)) {
                count++;
            }
        }
        return count;
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : matchResults.entrySet()) {
            totalPrize += (long) entry.getKey().getPrizeMoney() * entry.getValue();
        }
        double rawProfitRate = (double) totalPrize / purchaseAmount;
        // 요구사항 예시에 맞춰 소수점 셋째 자리에서 강제 버림 처리하여 오차 없는 결과 도출
        return Math.floor(rawProfitRate * 100) / 100.0;
    }

    public int getRankCount(Rank rank) {
        return matchResults.get(rank);
    }
}