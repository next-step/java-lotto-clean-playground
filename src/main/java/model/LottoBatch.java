package model;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoBatch {
    private final List<Lotto> lottos;

    public LottoBatch(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void add(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public List<Lotto> getAllLotto() {
        return List.copyOf(this.lottos);
    }

    public int getLottoCount() {
        return this.lottos.size();
    }
}
