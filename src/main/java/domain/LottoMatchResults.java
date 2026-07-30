package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMatchResults {
  private final List<LottoMatchResult> lottoMatchResults;
  private final int purchaseAmount;
  private final LottoRankTable lottoRankTable;

  public LottoMatchResults(List<LottoMatchResult> lottoMatchResults, int purchaseAmount) {
    this.lottoMatchResults = new ArrayList<>(lottoMatchResults);
    this.purchaseAmount = purchaseAmount;
    this.lottoRankTable = new LottoRankTable(this.lottoMatchResults);
  }

  public double profitRate(){
    return (double) lottoRankTable.sumPrize() / purchaseAmount;
  }

  public LottoRankTable rankTable(){
    return lottoRankTable;
  }
}
