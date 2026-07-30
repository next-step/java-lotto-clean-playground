package domain;

public class LottoMatchResult {
  private final LottoRank rank;

  public LottoMatchResult(Lotto lotto, WinningNumbers winningNumbers) {
    long matchingCount = winningNumbers.countMatch(lotto);
    boolean isMatchingBonusNumber = lotto.isMatchingBonusNumber(winningNumbers.getBonusNumber());
    this.rank = LottoRank.findByMatchingResult(matchingCount, isMatchingBonusNumber);
  }

  public LottoRank getRank() {
    return rank;
  }
}
