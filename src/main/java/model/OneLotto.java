package model;

import java.util.List;

public class OneLotto {
    private final List<Integer> lottoNumbers;

    public OneLotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
