package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoMatchResults {
  private final List<LottoMatchResult> lottoMatchResults;
  private final Integer purchaseAmount;
  private final LottoRankTable lottoRankTable;

  public LottoMatchResults(List<LottoMatchResult> lottoMatchResults, Integer purchaseAmount) {
    this.lottoMatchResults = new ArrayList<>(lottoMatchResults);
    this.purchaseAmount = purchaseAmount;
    this.lottoRankTable = new LottoRankTable(createRankTable());
  }

  public Long countByRank(LottoRank lottoRank){
    return lottoMatchResults.stream().filter(result->result.getRank() == lottoRank).count();
  }

  private Long sumPrize() {
    return lottoMatchResults.stream().mapToLong(LottoMatchResult::winningPrize).sum();
  }

  public Double profitRate(){
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
