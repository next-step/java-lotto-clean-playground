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

    public static Lottos generateAutoLottos(int numberOfTickets) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int count = 0; count < numberOfTickets; count++) {
            lottoList.add(Lotto.createLotto());
        }
        return new Lottos(lottoList);
    }

    public Lottos generatePassiveLottos(List<List<Integer>> passiveLottos) {
        List<Lotto> lottoList = new ArrayList<>();
        for (List<Integer> numbers : passiveLottos) {
            lottoList.add(Lotto.createLotto(numbers));
        }
        return new Lottos(lottoList);
    }

    public static Lottos mergeLottos(Lottos autoLottos, Lottos passiveLottos) {
        List<Lotto> mergedLottos = new ArrayList<>();
        mergedLottos.addAll(autoLottos.getLottos());
        mergedLottos.addAll(passiveLottos.getLottos());
        return new Lottos(mergedLottos);
    }
}
