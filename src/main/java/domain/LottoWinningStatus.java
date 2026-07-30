package domain;
import java.util.List;

public class LottoWinningStatus {
  private final LottoMatchResults lottoMatchResults;

  public LottoWinningStatus(List<Integer> winningNumberValues, Lottos lottos, int purchaseAmount, int bonusNumber) {
    final WinningNumbers winningNumbers = new WinningNumbers(winningNumberValues, bonusNumber);
    this.lottoMatchResults = new LottoMatchResults(lottos.createLottoMatchResult(winningNumbers), purchaseAmount);
  }

  public double profitRate() {
    return lottoMatchResults.profitRate();
  }

  public LottoRankTable rankTable() {
    return lottoMatchResults.rankTable();
  }
}
