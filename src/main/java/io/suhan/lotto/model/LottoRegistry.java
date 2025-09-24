package io.suhan.lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoRegistry {
    private final List<Lotto> lottoList;

    public LottoRegistry() {
        this.lottoList = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        lottoList.add(lotto);
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }
}
