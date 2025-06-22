package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public record Lottos(List<Lotto> lottos) implements Iterable<Lotto> {

  public Lottos {
    lottos = new ArrayList<>(lottos); // 불변성을 위한 방어적 복사
  }

  public void add(final Lotto lotto) {
    lottos.add(lotto);
  }

  public void addAll(final Lottos manualLottos) {
    lottos.addAll(manualLottos.lottos);
  }

  public int size() {
    return lottos.size();
  }

  public static Lottos of(List<Lotto> lottos) {
    return new Lottos(lottos);
  }

  @Override
  public Iterator<Lotto> iterator() {
    return lottos.iterator();
  }

  @Override
  public List<Lotto> lottos() {
    return lottos;
  }
}
