package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public static Lottos generateLottos(int numberOfTickets) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int count = 0; count < numberOfTickets; count++) {
            lottoList.add(Lotto.createLotto());
        }
        return new Lottos(lottoList);
    }
}
