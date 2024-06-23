package domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Lotto {

    private final List<LottoNumber> lottoNumber;

    public Lotto(CreateLottoNumber createLottoNumber) {
        this.lottoNumber = makeAutoLotto(createLottoNumber);
    }

    public Lotto(List<Integer> inputPassiveLottoNumber) {
        this.lottoNumber = makePassiveLotto(inputPassiveLottoNumber);
    }

    public List<LottoNumber> getLottoNumber() {
        return lottoNumber;
    }

    private List<LottoNumber> makeAutoLotto(CreateLottoNumber createLottoNumber) {
        List<Integer> singleLotto = new ArrayList<>(createLottoNumber.getRandomLottoNumber());
        sortLottoNumber(singleLotto);
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int lottoNumberElement : singleLotto) {
            LottoNumber lottoNumber = new LottoNumber(lottoNumberElement);
            lottoNumbers.add(lottoNumber);
        }
        return lottoNumbers;
    }

    private List<LottoNumber> makePassiveLotto(List<Integer> inputPassiveLottoNumber) {
        List<Integer> passiveLottoNumber = new ArrayList<>(inputPassiveLottoNumber);
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int lottoNumberElement : passiveLottoNumber) {
            LottoNumber lottoNumber = new LottoNumber(lottoNumberElement);
            lottoNumbers.add(lottoNumber);
        }
        return lottoNumbers;
    }

    private void sortLottoNumber(List<Integer> lottoNumber) {
        Collections.sort(lottoNumber);
    }
}
