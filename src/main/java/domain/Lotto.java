package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private final List<Integer> lottoNumber;

    public Lotto(CreateLottoNumber createLottoNumber) {
        this.lottoNumber = makeAutoLotto(createLottoNumber);
    }

    public Lotto(List<Integer> inputPassiveLottoNumber) {
        this.lottoNumber = makePassiveLotto(inputPassiveLottoNumber);
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }

    private List<Integer> makeAutoLotto(CreateLottoNumber createLottoNumber) {
        List<Integer> singleLotto = new ArrayList<>(createLottoNumber.getRandomLottoNumber());
        sortLottoNumber(singleLotto);
        return singleLotto;
    }

    private List<Integer> makePassiveLotto(List<Integer> inputPassiveLottoNumber) {
        return new ArrayList<>(inputPassiveLottoNumber);
    }

    private void sortLottoNumber(List<Integer> lottoNumber) {
        Collections.sort(lottoNumber);
    }
}
