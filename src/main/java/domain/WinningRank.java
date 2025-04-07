package domain;

import java.util.Arrays;

public enum WinningRank {
  NONE(0, 0L, false, "상금 없음"),
  THREE(3, 5_000L, false, "3개 일치 (5000원)"),
  FOUR(4, 50_000L, false, "4개 일치 (50000원)"),
  FIVE(5, 1_500_000L, false, "5개 일치 (1500000원)"),
  FIVE_WITH_BONUS(5, 30_000_000L, true, "5개 일치, 보너스 볼 일치 (30000000원)"),
  SIX(6, 2_000_000_000L, false, "6개 일치 (2000000000원)");

  private final int matchCount;
  private final long prize;
  private final boolean isBonus;
  private final String description;

  WinningRank(int matchCount, long prize, boolean isBonus, String description) {
    this.matchCount = matchCount;
    this.prize = prize;
    this.isBonus = isBonus;
    this.description = description;
  }

  public int getMatchCount() {
    return matchCount;
  }

  public long getPrize() {
    return prize;
  }

  public String getDescription() {
    return description;
  }

  public static WinningRank valueof(int matches, boolean hasBonus) {
    return Arrays.stream(values())
        .filter(WinningRank -> WinningRank.matchCount == matches)
        .filter(WinningRank -> WinningRank.isBonus == hasBonus)
        .findFirst()
        .orElse(WinningRank.NONE);
  }
}
