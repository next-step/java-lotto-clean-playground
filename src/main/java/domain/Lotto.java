package domain;

import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumber;

    public Lotto(List<LottoNumber> lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public List<LottoNumber> getLottoNumber() {
        return this.lottoNumber;
    }
}
