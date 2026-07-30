package domain;

public class LottoMatchResult {
  private final LottoRank rank;

  public LottoMatchResult(Lotto lotto, WinningNumbers winningNumbers) {
    long matchingCount = winningNumbers.countMatch(lotto);
    this.rank = LottoRank.findByMatchingCount(matchingCount);
  }

  public LottoRank getRank() {
    return rank;
  }
}
