package domain;
import java.util.List;

public class LottoWinningStatus {
  private final WinningNumbers winningNumbers;
  private final Lottos lottos;
  private final LottoMatchResults lottoMatchResults;

  public LottoWinningStatus(List<Integer> winningNumbers, Lottos lottos, int purchaseAmount) {
    this.winningNumbers = new WinningNumbers(winningNumbers);
    this.lottos = lottos;
    this.lottoMatchResults = new LottoMatchResults(lottos.createLottoMatchResult(this.winningNumbers), purchaseAmount);
  }

  public double profitRate(){
    return lottoMatchResults.profitRate();
  }

  public LottoRankTable rankTable(){
    return lottoMatchResults.rankTable();
  }
}
