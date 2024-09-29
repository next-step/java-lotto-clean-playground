package domain;

import java.util.List;

public class Lotto {

    List<Integer> lottoNumber;

    public Lotto(List<Integer> lotto) {
        this.lottoNumber = lotto;
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
