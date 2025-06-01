package lotto.model;

import java.util.List;

public class Lotto {

    private final List<LottoNumbers> lottoNumbers;

    public Lotto(List<LottoNumbers> lottoNumbers) {
        this.lottoNumbers = List.copyOf(lottoNumbers);
    }

    public List<LottoNumbers> asList() {
        return lottoNumbers;
    }

    public int size() {
        return lottoNumbers.size();
    }

}
