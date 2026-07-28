package domain.lotto;

import domain.number.LottoNumber;
import domain.number.LottoNumberCombination;

public class BonusBall {
    private final LottoNumber lottoNumber;

    private BonusBall(LottoNumber lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static BonusBall from(int number) {
        return new BonusBall(LottoNumber.from(number));
    }

    public void validateNotDuplicatedWith(LottoNumberCombination winningNumbers) {
        if (winningNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoNumber lottoNumber() {
        return lottoNumber;
    }
}
