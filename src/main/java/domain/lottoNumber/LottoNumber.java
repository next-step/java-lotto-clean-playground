package domain.lottoNumber;

import java.util.List;

public class LottoNumber {
    private final List<Integer> lottoNumber;

    public LottoNumber(List<Integer> lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public List<Integer> getLottoNumber() {
        return this.lottoNumber;
    }
}
