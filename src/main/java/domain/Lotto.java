package domain;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumber){
        sortLottoNumber(lottoNumber);
        this.lottoNumbers = lottoNumber;
    }

    private void sortLottoNumber(List<Integer> lottoNumber){
        Collections.sort(lottoNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
