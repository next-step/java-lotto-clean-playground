package lotto;

public record LottoNumber(int number) {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    public LottoNumber {
        validate(number);
    }

    private void validate(int value) {
        if (value < MINIMUM_NUMBER || value > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
