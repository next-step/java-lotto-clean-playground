package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> lottoNumber;

    public Lotto(CreateLottoNumber createLottoNumber) {
        this.lottoNumber = makeLotto(createLottoNumber);
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }

    private List<Integer> makeLotto(CreateLottoNumber createLottoNumber) {
        List<Integer> singleLotto = new ArrayList<>(createLottoNumber.getRandomLottoNumber());
        sortLottoNumber(singleLotto);
        return singleLotto;
    }

    private void sortLottoNumber(List<Integer> lottoNumber) {
        Collections.sort(lottoNumber);
    }
}
