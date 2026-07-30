package domain;

import java.util.Arrays;

public enum LottoRank {
  FIRST(6, false, 2000000000L),
  SECOND(5, true, 30000000L),
  THIRD(5, false, 15000000L),
  FOUR(4, false, 50000L),
  FIVE(3, false, 5000L),
  MISS(0, false, 0L);

  private final int matchingCount;
  private final boolean isMatchingBonusNumber;
  private final long prize;

  LottoRank(int matchingCount, boolean isMatchingBonusNumber, long prize) {
    this.matchingCount = matchingCount;
    this.isMatchingBonusNumber = isMatchingBonusNumber;
    this.prize = prize;
  }

  public int getMatchingCount() {
    return matchingCount;
  }

  public long getPrize() {
    return prize;
  }

  public boolean isMatchingBonusNumber() {
    return isMatchingBonusNumber;
  }

  public static LottoRank findByMatchingResult(long matchingCount, boolean isMatchingBonusNumber){
    return Arrays.stream(LottoRank.values())
        .filter(value -> value.matchingCount == matchingCount)
        .filter(value -> value.satisfiesBonusCondition(isMatchingBonusNumber))
        .findFirst()
        .orElse(MISS);
  }

  private boolean satisfiesBonusCondition(boolean isMatchingBonusNumber){
    if (this.isMatchingBonusNumber) {
      return isMatchingBonusNumber;
    }
    return true;
  }
}
