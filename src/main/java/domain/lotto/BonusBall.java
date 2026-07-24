package domain.lotto;

import domain.number.LottoNumber;

public class BonusBall {
    private final LottoNumber lottoNumber;

    private BonusBall(LottoNumber lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static BonusBall from(int number) {
        return new BonusBall(LottoNumber.from(number));
    }

    public boolean matches(Lotto lotto) {
        return lotto.contains(lottoNumber);
    }
}
