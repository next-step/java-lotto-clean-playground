package model;

public class LottoNumber {

    private static final int LOTTO_MINIMUM_NUMBER = LottoConstants.LOTTO_MINIMUM_NUMBER.getValue();
    private static final int LOTTO_MAXIMUM_NUMBER = LottoConstants.LOTTO_MAXIMUM_NUMBER.getValue();
    private final int number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private void validateNumber(int number) {
        if (number < LOTTO_MINIMUM_NUMBER || number > LOTTO_MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }
}
