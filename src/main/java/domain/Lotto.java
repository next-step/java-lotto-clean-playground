package domain;

import java.util.List;

public class Lotto {

    private List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getBonusNumber() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        return lottoNumbers.generateBonusNumber();
    }

    public static Lotto createLotto() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        return new Lotto(lottoNumbers.generateLottoNumber());
    }

    public static Lotto createLotto(List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

}
