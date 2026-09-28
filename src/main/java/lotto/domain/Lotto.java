package lotto.domain;
import java.util.List;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    public Lotto(List<Integer> values) {
        this.lottoNumbers = new LottoNumbers(values);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public int countMatch(WinningNumbers winningNumbers) {
        int matchCount = 0;
        for (LottoNumber number : lottoNumbers.getLottoNumbers()) {
            matchCount += matchScore(number, winningNumbers);
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
