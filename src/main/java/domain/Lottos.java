package domain;

import java.util.List;

public class Lottos {
    private final List<LottoNumber> lottos;

    public Lottos(List<LottoNumber> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }
    public List<LottoNumber> getLottos() {
        return lottos;
    }
}
