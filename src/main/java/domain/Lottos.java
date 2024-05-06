package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private int lottoAmount;
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(int lottoAmount) {
        this.lottoAmount = lottoAmount;
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}