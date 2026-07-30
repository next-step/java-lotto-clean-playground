package domain;

import java.util.Arrays;

public enum LottoRank {
  FIRST(6, 2000000000L),
  SECOND(5, 15000000L),
  THIRD(4, 50000L),
  FOUR(3, 5000L),
  MISS(0, 0L);

  private final int matchingCount;
  private final long prize;

  LottoRank(int matchingCount, long prize) {
    this.matchingCount = matchingCount;
    this.prize = prize;
  }

  public int getMatchingCount() {
    return matchingCount;
  }

  public long getPrize() {
    return prize;
  }

  public static LottoRank findByMatchingCount(long matchingCount){
    return Arrays.stream(LottoRank.values()).filter(value ->value.matchingCount == matchingCount).findAny().orElse(MISS);
  }
}
