package domain;

import java.util.List;

public class Lotto {

    private final List<Integer> lottoNumber;

    public Lotto(final List<Integer> lotto) {
        this.lottoNumber = lotto;
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
