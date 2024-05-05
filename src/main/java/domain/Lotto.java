package domain;

import java.util.List;

public class Lotto {

    private List<Integer> lottoNumber;
    private static LottoNumberMaker numberMaker = new LottoNumberMaker();

    public Lotto() {
        this.lottoNumber = numberMaker.getLottoNumbers();
    }

    public Lotto(List<Integer> manualNumber){
        this.lottoNumber = manualNumber;
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
