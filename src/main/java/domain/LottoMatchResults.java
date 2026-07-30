package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMatchResults {
  private final int purchaseAmount;
  private final LottoRankTable lottoRankTable;

  public LottoMatchResults(List<LottoMatchResult> lottoMatchResults, int purchaseAmount) {
    this.purchaseAmount = purchaseAmount;
    this.lottoRankTable = new LottoRankTable(new ArrayList<>(lottoMatchResults));
  }

  public double profitRate() {
    return (double) lottoRankTable.sumPrize() / purchaseAmount;
  }

  public LottoRankTable rankTable() {
    return lottoRankTable;
  }
}
