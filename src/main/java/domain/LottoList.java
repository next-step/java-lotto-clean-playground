package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoList {

    private final List<Lotto> lottoList = new ArrayList<>();
    private int numberOfLotto;

    public LottoList() {

    }

    public void addLotto(Lotto lotto) {
        lottoList.add(lotto);
        numberOfLotto++;
    }

    public List<Lotto> getLottoList() {
        return this.lottoList;
    }

    public int getNumberOfLotto() {
        return this.numberOfLotto;
    }
}
