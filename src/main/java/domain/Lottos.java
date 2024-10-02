package domain;

import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    //정적 팩토리 메서드
    public static Lottos fromAutoLottos(int numberOfAutoLottos) {
        return LottoFactory.createAutoLottos(numberOfAutoLottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
