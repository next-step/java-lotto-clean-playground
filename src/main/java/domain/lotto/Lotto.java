package domain.lotto;

import domain.number.LottoNumbers;
import domain.number.LottoNumber;
import java.util.List;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto(List<Integer> numbers) {
        this(LottoNumbers.from(numbers));
    }

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<Integer> values() {
        return lottoNumbers.values();
    }
}
