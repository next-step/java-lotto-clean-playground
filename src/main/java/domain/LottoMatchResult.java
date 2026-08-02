package domain;

public class LottoMatchResult {
  private final LottoRank rank;

  public LottoMatchResult(Lotto lotto, WinningNumbers winningNumbers) {
    long matchingCount = winningNumbers.matchCount(lotto);
    boolean isMatchingBonusNumber = winningNumbers.hasBonusNumber(lotto);
    this.rank = LottoRank.findByMatchingResult(matchingCount, isMatchingBonusNumber);
  }

  public LottoRank getRank() {
    return rank;
  }
}
