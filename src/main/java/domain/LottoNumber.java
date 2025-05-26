package domain;

//단일 로또 숫자 1개
public class LottoNumber {
    private static final int MIN_NUM = 1;
    private static final int MAX_NUM = 45;
    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < MIN_NUM || number > MAX_NUM) {
            throw new IllegalArgumentException();
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

