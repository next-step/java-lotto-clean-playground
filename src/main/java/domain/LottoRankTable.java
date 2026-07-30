package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoRankTable {
  private final Map<LottoRank, Long> lottoRankTable;

  public LottoRankTable(Map<LottoRank, Long> lottoRankTable) {
    this.lottoRankTable = new HashMap<>(lottoRankTable);
  }

  public long countOf(LottoRank lottoRank){
    return lottoRankTable.get(lottoRank);
  }
}
