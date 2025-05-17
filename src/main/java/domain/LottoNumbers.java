package domain;

import java.util.List;

public record LottoNumbers(List<LottoNumber> lottoNumbers) {
    public LottoNumbers {
        lottoNumbers = List.copyOf(lottoNumbers);
    }

    public int size() {
        return lottoNumbers.size();
    }
}
