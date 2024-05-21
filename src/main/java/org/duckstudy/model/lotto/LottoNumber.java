package org.duckstudy.model.lotto;

public record LottoNumber(Integer value) {

    private static final int START_INCLUSIVE_NUMBER = 1;
    private static final int END_EXCLUSIVE_NUMBER = 46;

    public LottoNumber {
        validateNumber(value);
    }

    private void validateNumber(Integer number) {
        if (number < START_INCLUSIVE_NUMBER || number >= END_EXCLUSIVE_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45까지의 숫자여야 합니다.");
        }
    }
}
