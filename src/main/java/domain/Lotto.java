package domain;

import java.util.List;

public class Lotto {

    private List<Integer> lottoNumber;

    public Lotto() {
        LottoNumberMaker numberMaker = new LottoNumberMaker();
        this.lottoNumber = numberMaker.getLottoNumbers();
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
