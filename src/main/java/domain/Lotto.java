package domain;

import java.util.HashSet;
import java.util.Set;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    public Lotto() {
        this.lottoNumbers = generateRandomLottoNumbers();
    }

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    private LottoNumbers generateRandomLottoNumbers() {
        Set<Integer> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LottoNumbers.NUMBER_COUNT) {
            lottoNumbers.add((int)(Math.random() * LottoNumber.MAX_NUMBER) + 1);
        }
        return new LottoNumbers(lottoNumbers);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
