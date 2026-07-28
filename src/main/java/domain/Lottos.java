package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

  private final List<Lotto> lottos;

  public Lottos(List<Lotto> lottos) {
    this.lottos = new ArrayList<>(lottos);
  }

  public List<Lotto> getLottos() {
    return Collections.unmodifiableList(lottos);
  }

  public List<List<Integer>> purchasedLottoNumbers() {
    return lottos.stream().map(Lotto::getLottoNumbers).toList();
  }
}
