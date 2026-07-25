package domain.lotto;

import domain.number.LottoNumbers;
import domain.number.MatchCount;
import domain.number.LottoNumber;
import domain.result.LottoResult;
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

    public LottoResult match(WinningLotto winningLotto) {
        return LottoResult.of(countMatching(winningLotto), matchesBonusBall(winningLotto));
    }

    private boolean matchesBonusBall(WinningLotto winningLotto) {
        return winningLotto.bonusBall()
                .filter(this::matches)
                .isPresent();
    }

    private boolean matches(BonusBall bonusBall) {
        return contains(bonusBall.lottoNumber());
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<Integer> values() {
        return lottoNumbers.values();
    }
}
