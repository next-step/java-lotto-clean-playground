package domain;

import java.util.List;

public class Lottos {
    //구매한 로또들의 내역
    //일급 컬렉션
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }
}


