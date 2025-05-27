package lotto.domain;

public record LottoNumber(int value) {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public LottoNumber {
        if (value < MINIMUM_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 ~45 사이의 숫자 입니다.");
        }
    }
    public int getValue() {
        return value();
    }
}
