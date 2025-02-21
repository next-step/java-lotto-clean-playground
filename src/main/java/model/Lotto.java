package model;

import java.util.*;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    private Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto getRandomLotto() {
        return new Lotto(LottoNumbers.getRandomLottoNumbers());
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers.getCopy();
    }

}
