package model;

import java.util.List;

public class LottoNumber {
    private List<Integer> lottoNumbers;
    private int rank;

    public LottoNumber(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
