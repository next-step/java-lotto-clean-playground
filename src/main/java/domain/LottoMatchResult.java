package domain;

public class LottoMatchResult {
  private final Lotto lotto;
  private final Long matchingCount;
  private final LottoRank rank;

  public LottoMatchResult(Lotto lotto, WinningNumbers winningNumbers) {
    this.lotto = lotto;
    this.matchingCount = lotto.countMatch(winningNumbers);
    this.rank = LottoRank.findByMatchingCount(matchingCount);
  }

  public LottoRank getRank() {
    return rank;
  }

  public Long winningPrize() {
    return rank.getPrize();
  }
}
