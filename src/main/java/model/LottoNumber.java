package model;

public class LottoNumber {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private final int number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }
}
