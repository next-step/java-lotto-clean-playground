package io.suhan.lotto.model.lotto;

public record LottoNumber(int value) {
    public LottoNumber {
        if (value < Lotto.LOTTO_NUMBER_MIN || value > Lotto.LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("로또 번호는 " + Lotto.LOTTO_NUMBER_MIN + "~" + Lotto.LOTTO_NUMBER_MAX + " 사이여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
