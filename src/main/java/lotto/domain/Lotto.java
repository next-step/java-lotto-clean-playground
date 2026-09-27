package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;


    public Lotto(List<Integer> numbers) {
        this.lottoNumbers = toLottoNumbers(numbers);
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public int countMatch(WinningNumbers winningNumbers) {
        int matchCount = 0;
        for (LottoNumber lottoNumber : lottoNumbers) {
            matchCount += matchScore(lottoNumber, winningNumbers);
        }
        return matchCount;
    }

    private int matchScore(LottoNumber number, WinningNumbers winningNumbers) {
        if (winningNumbers.contains(number)) {
            return 1;
        }
        return 0;
    }


}
