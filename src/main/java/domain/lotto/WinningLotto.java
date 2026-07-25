package domain.lotto;

import domain.number.LottoNumber;
import domain.number.LottoNumbers;
import java.util.List;
import java.util.Optional;

public class WinningLotto {
    private final LottoNumbers lottoNumbers;
    private final Optional<BonusBall> bonusBall;

    private WinningLotto(LottoNumbers lottoNumbers, Optional<BonusBall> bonusBall) {
        this.lottoNumbers = lottoNumbers;
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    public static WinningLotto from(List<Integer> numbers) {
        return new WinningLotto(LottoNumbers.from(numbers), Optional.empty());
    }

    public static WinningLotto of(List<Integer> numbers, BonusBall bonusBall) {
        return new WinningLotto(LottoNumbers.from(numbers), Optional.of(bonusBall));
    }

    private void validateBonusBall(Optional<BonusBall> bonusBall) {
        if (bonusBall.filter(ball -> lottoNumbers.contains(ball.lottoNumber())).isPresent()) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Optional<BonusBall> bonusBall() {
        return bonusBall;
    }
}
