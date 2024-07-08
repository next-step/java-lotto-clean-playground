package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoList {

    private final List<Lotto> lottoList = new ArrayList<>();
    private int numberOfLotto;
    private int numberOfManualLotto;

    public LottoList() {

    }

    public void addAutoLotto(Lotto lotto) {
        lottoList.add(lotto);
        numberOfLotto++;
    }

    public void addManualLotto(Lotto lotto) {
        lottoList.add(lotto);
        numberOfManualLotto++;
        numberOfLotto++;
    }

    public List<Lotto> getLottoList() {
        return this.lottoList;
    }

    public int getNumberOfLotto() {
        return this.numberOfLotto;
    }

    public int getNumberOfManualLotto() {
        return this.numberOfManualLotto;
    }
}
