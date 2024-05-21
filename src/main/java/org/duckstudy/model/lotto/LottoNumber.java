package org.duckstudy.model.lotto;

public class LottoNumber {

    private static final int START_INCLUSIVE_NUMBER = 1;
    private static final int END_EXCLUSIVE_NUMBER = 46;

    private final int value;

    public LottoNumber(int number) {
        validateNumber(number);

        this.value = number;
    }

    private void validateNumber(int number) {
        if (number < START_INCLUSIVE_NUMBER || number >= END_EXCLUSIVE_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45까지의 숫자여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
