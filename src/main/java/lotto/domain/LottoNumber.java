package lotto.domain;

public record LottoNumber(int number) {
    public static final int MAX_NUMBER = 45;

    public LottoNumber {
        validate(number);
    }

    private void validate(int value) {
        if (value <= 0 || value > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 이상 45 이하여야 한다.");
        }
    }
}
