package model;

import java.util.ArrayList;
import java.util.List;

public class LottoBatch {
    private final List<Lotto> lottos;

    public LottoBatch() {
        this.lottos = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public void addAll(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    public List<Lotto> getAllLotto() {
        return List.copyOf(this.lottos);
    }

    public int getLottoCount() {
        return this.lottos.size();
    }
}
