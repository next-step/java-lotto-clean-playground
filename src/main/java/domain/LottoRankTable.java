package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoRankTable {
  private final Map<LottoRank, Long> lottoRankTable;

  public LottoRankTable(List<LottoMatchResult> lottoMatchResults) {
    this.lottoRankTable = createRankTable(lottoMatchResults);
  }

  public long countOf(LottoRank lottoRank){
    return lottoRankTable.get(lottoRank);
  }

  private Map<LottoRank, Long> createRankTable(List<LottoMatchResult> lottoMatchResults){
    Map<LottoRank, Long> rankCounts = new HashMap<>();
    for(LottoRank lottoRank : LottoRank.values()){
      rankCounts.put(lottoRank, countByRank(lottoRank, lottoMatchResults));
    }
    return rankCounts;
  }

  private long countByRank(LottoRank lottoRank, List<LottoMatchResult> lottoMatchResults){
    return lottoMatchResults.stream().filter(result->result.getRank() == lottoRank).count();
  }

  public long sumPrize() {
    return lottoRankTable.entrySet().stream().mapToLong(value -> value.getKey().getPrize() * value.getValue()).sum();
  }
}
