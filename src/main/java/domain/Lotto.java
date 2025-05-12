package domain;

import exception.InvalidLottoNumberCountException;
import exception.LottoNumberDuplicationException;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getNumbers() {
        return lottoNumbers;
    }

    private void validate(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new InvalidLottoNumberCountException("로또 숫자 개수가 6개가 아닙니다.");
        }

        if (new HashSet<>(lottoNumbers).size() != LOTTO_NUMBER_COUNT) {
            throw new LottoNumberDuplicationException("로또의 숫자가 중복됩니다.");
        }
    }
}
