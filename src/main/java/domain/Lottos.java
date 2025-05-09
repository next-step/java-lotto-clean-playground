package domain;

import enums.LottoType;

import java.util.Collections;
import java.util.List;

public class Lottos {

    List<Lotto> lottos;

    public Lottos(List<Lotto> allLottos) {
        this.lottos = allLottos;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int countByType(LottoType type) {
        return (int) lottos.stream()
                .filter(lotto -> lotto.getType() == type)
                .count();
    }

    public LottoCount getManualCount() {
        return LottoCount.from(countByType(LottoType.MANUAL));
    }

    public LottoCount getAutoCount() {
        return LottoCount.from(countByType(LottoType.AUTO));
    }

    public LottoCount getLottoCount() {
        return LottoCount.from(lottos.size());
    }
}
