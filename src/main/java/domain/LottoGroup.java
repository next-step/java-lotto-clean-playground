package domain;

import java.util.List;

public class LottoGroup {

    private final List<Lotto> lottos;

    public LottoGroup(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<List<Integer>> getAllLottoNumbers(){
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }
}
