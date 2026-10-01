package lotto.domain;

public record LottoNumber(int number) {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    public LottoNumber {
        validate(number);
    }

    private void validate(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException
                    ("로또 번호는" + MIN_NUMBER + " 이상 " + MAX_NUMBER + " 이하여야 한다.");
        }
    }
}
