package domain.lotto;

import domain.number.LottoNumbers;
import domain.number.MatchCount;
import domain.result.LottoResult;
import java.util.List;
import java.util.Optional;

public class WinningLotto {
    private final LottoNumbers lottoNumbers;
    private final BonusBall bonusBall;

    private WinningLotto(LottoNumbers lottoNumbers, BonusBall bonusBall) {
        this.lottoNumbers = lottoNumbers;
        if (bonusBall != null) {
            lottoNumbers.validateNotContains(bonusBall.lottoNumber());
        }
        this.bonusBall = bonusBall;
    }

    public static WinningLotto from(List<Integer> numbers) {
        return new WinningLotto(LottoNumbers.from(numbers), null);
    }

    public static WinningLotto of(List<Integer> numbers, BonusBall bonusBall) {
        return new WinningLotto(LottoNumbers.from(numbers), bonusBall);
    }

    public static WinningLotto from(LottoNumbers lottoNumbers) {
        return new WinningLotto(lottoNumbers, null);
    }

    public static WinningLotto of(LottoNumbers lottoNumbers, BonusBall bonusBall) {
        return new WinningLotto(lottoNumbers, bonusBall);
    }

    public Optional<BonusBall> bonusBall() {
        return Optional.ofNullable(bonusBall);
    }

    public MatchCount countMatching(Lotto lotto) {
        return lottoNumbers.countMatching(LottoNumbers.from(lotto.values()));
    }

    public LottoResult match(Lotto lotto) {
        MatchCount matchCount = countMatching(lotto);
        return LottoResult.of(matchCount, isBonusBallMatched(lotto));
    }

    private boolean isBonusBallMatched(Lotto lotto) {
        return bonusBall()
                .map(bonusBall -> lotto.contains(bonusBall.lottoNumber()))
                .orElse(false);
    }
}
