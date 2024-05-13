package model;

public class LottoNumber {
    private final int number;

    public LottoNumber(String number) {
        try {
            int parsedNumber = Integer.parseInt(number);
            validateRange(parsedNumber);
            this.number = parsedNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Constant.VALUE_EXCEPTION, e);
        }
    }

    private void validateRange(int number) {
        if (number < Constant.MIN_LOTTO_NUMBER || number > Constant.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(Constant.RANGE_EXCEPTION);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
