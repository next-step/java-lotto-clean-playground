package domain.lottoNumber;

import java.util.List;

public class LottoNumber {
    private List<Integer> lotoNumber;

    public LottoNumber(List<Integer> lotoNumber) {
        this.lotoNumber = lotoNumber;
    }

    public List<Integer> getLotoNumber() {
        return this.lotoNumber;
    }
}
