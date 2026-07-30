package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoMatchResults {
  private final List<LottoMatchResult> lottoMatchResults;
  private final int purchaseAmount;
  private final LottoRankTable lottoRankTable;

  public LottoMatchResults(List<LottoMatchResult> lottoMatchResults, int purchaseAmount) {
    this.lottoMatchResults = new ArrayList<>(lottoMatchResults);
    this.purchaseAmount = purchaseAmount;
    this.lottoRankTable = new LottoRankTable(createRankTable());
  }

  public long countByRank(LottoRank lottoRank){
    return lottoMatchResults.stream().filter(result->result.getRank() == lottoRank).count();
  }

  private long sumPrize() {
    return lottoMatchResults.stream().mapToLong(LottoMatchResult::winningPrize).sum();
  }

  public double profitRate(){
    return (double) sumPrize() / purchaseAmount;
  }

  private Map<LottoRank, Long> createRankTable(){
    Map<LottoRank, Long> rankCounts = new HashMap<>();
    for(LottoRank lottoRank : LottoRank.values()){
      rankCounts.put(lottoRank, countByRank(lottoRank));
    }
    return rankCounts;
  }

  public LottoRankTable rankTable(){
    return lottoRankTable;
  }
}
