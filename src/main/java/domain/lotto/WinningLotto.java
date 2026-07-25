package domain.lotto;

import domain.number.LottoNumber;
import domain.number.LottoNumbers;
import java.util.List;
import java.util.Optional;

public class WinningLotto {
    private final LottoNumbers lottoNumbers;
    private final BonusBall bonusBall; // nullable

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

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Optional<BonusBall> bonusBall() {
        return Optional.ofNullable(bonusBall);
    }
}
