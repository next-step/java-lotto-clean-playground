package model;

import model.lotto.Lotto;

import static utils.LottoConstants.*;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int number;

    private LottoNumber(int number) {
        validateLottoNumberBound(number);
        this.number = number;
    }

    public static LottoNumber from(int number) {
        return new LottoNumber(number);
    }

    public static LottoNumber bonusNumber(int bonusNumber, Lotto winningNumber) {
        LottoNumber inputBonusNumber = LottoNumber.from(bonusNumber);
        if (winningNumber.contains(inputBonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복되면 안 됩니다!");
        }
        return new LottoNumber(bonusNumber);
    }

    private static void validateLottoNumberBound(int number) {
        if (number > LOTTO_MAX_NUMBER || number < LOTTO_MIN_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_MIN_NUMBER + "이상 " + LOTTO_MAX_NUMBER + "이하의 정수입니다!");
        }
    }

    public boolean isGraterThan(int number) {
        return this.number > number;
    }

    public boolean isLessThan(int number) {
        return this.number < number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber lottoNumber = (LottoNumber) o;
        return this.number == lottoNumber.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
