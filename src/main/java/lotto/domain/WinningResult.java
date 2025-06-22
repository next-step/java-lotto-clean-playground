package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

  private final Map<Rank, Integer> rankStats;

  public WinningResult(final Map<Rank, Integer> rankStats) {
    this.rankStats = rankStats;
  }

  public Map<Rank, Integer> getWinningResult() {
    return new EnumMap<>(rankStats);
  }

  public double calculateReturnRate(Long totalPurchaseAmount) {
    return (double) calculateTotalPrize() / totalPurchaseAmount;
  }

  public long calculateTotalPrize() {
    long totalPrize = 0;

    for (Map.Entry<Rank, Integer> entry : rankStats.entrySet()) {
      totalPrize += (long) entry.getKey().getPrizeMoney() * entry.getValue();
    }

    return totalPrize;
  }
}
