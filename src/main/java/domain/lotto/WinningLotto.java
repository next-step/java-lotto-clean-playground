package domain.lotto;

import domain.number.LottoNumber;
import domain.number.LottoNumbers;
import java.util.List;

public class WinningLotto {
    private final LottoNumbers lottoNumbers;

    private WinningLotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static WinningLotto from(List<Integer> numbers) {
        return new WinningLotto(LottoNumbers.from(numbers));
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
