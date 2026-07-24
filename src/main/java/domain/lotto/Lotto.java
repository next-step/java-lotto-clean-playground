package domain.lotto;

import domain.number.LottoNumbers;
import domain.number.MatchCount;
import java.util.List;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto(List<Integer> numbers) {
        this(LottoNumbers.from(numbers));
    }

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public MatchCount countMatching(WinningLotto winningLotto) {
        return lottoNumbers.countMatching(winningLotto);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
