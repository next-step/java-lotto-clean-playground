package model;

import java.util.List;

public class LottoBatch {
    List<Lotto> lottos;

    public LottoBatch(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void add(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public List<Lotto> getAllLotto() {
        return List.copyOf(this.lottos);
    }
}
