package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

  private final List<Lotto> lottos;

  public Lottos(List<Lotto> lottos) {
    this.lottos = new ArrayList<>(lottos);
  }

  public List<List<Integer>> purchasedLottoNumbers() {
    return lottos.stream().map(Lotto::getLottoNumbers).toList();
  }

  public List<LottoMatchResult> createLottoMatchResult(WinningNumbers winningNumbers){
    return lottos.stream().map(lotto -> new LottoMatchResult(lotto, winningNumbers)).toList();
  }
}
