package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Rank {
  NONE(0, false, 0),
  FIFTH(3, false, 5_000),
  FOURTH(4, false, 50_000),
  THIRD(5, false, 1_500_000),
  SECOND(5, true, 30_000_000),
  FIRST(6, false, 2_000_000_000);

  private final int matchCount;
  private final boolean bonusMatch;
  private final int prizeMoney;

  private static final Map<Integer, Rank> MATCH_COUNT_TO_RANK =
      Arrays.stream(values())
          .filter(rank -> rank != SECOND)
          .collect(Collectors.toMap(rank -> rank.matchCount, rank -> rank));

  Rank(int matchCount, boolean bonusMatch, int prizeMoney) {
    this.matchCount = matchCount;
    this.bonusMatch = bonusMatch;
    this.prizeMoney = prizeMoney;
  }

  public int getMatchCount() {
    return this.matchCount;
  }

  public int getPrizeMoney() {
    return this.prizeMoney;
  }

  public static Rank valueOf(int matchCount, boolean bonusMatch) {
    validateMatchCount(matchCount);

    if (matchCount == 6) {
      return FIRST;
    }
    if (matchCount == 5) {
      return bonusMatch ? SECOND : THIRD;
    }
    if (matchCount == 4) {
      return FOURTH;
    }
    if (matchCount == 3) {
      return FIFTH;
    }
    return NONE;
  }

  public static Rank valueOf(int matchCount) {
    validateMatchCount(matchCount);
    return MATCH_COUNT_TO_RANK.getOrDefault(matchCount, NONE);
  }

  public boolean isBonusMatch() {
    return this.bonusMatch;
  }

  private static void validateMatchCount(final int matchCount) {
    if (matchCount < 0) {
      throw new RuntimeException("적중 갯수는 음수가 될 수 없습니다.");
    }
  }
}