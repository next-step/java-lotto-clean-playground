package domain;

public class LottoMatchResult {
  private final Lotto lotto;
  private final long matchingCount;
  private final LottoRank rank;

  public LottoMatchResult(Lotto lotto, WinningNumbers winningNumbers) {
    this.lotto = lotto;
    this.matchingCount = lotto.countMatch(winningNumbers);
    this.rank = LottoRank.findByMatchingCount(matchingCount);
  }

  public LottoRank getRank() {
    return rank;
  }

  public long winningPrize() {
    return rank.getPrize();
  }
}
