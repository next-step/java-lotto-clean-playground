package lotto;

public record LottoNumber(int number) {
    public static int MIN_NUMBER = 1;
    public static int MAX_NUMBER = 45;

    public LottoNumber {
        if (MIN_NUMBER > number) {
            throw new IllegalArgumentException("숫자가 1보다 작습니다.");
        }
        if (MAX_NUMBER < number) {
            throw new IllegalArgumentException("숫자가 45보다 큽니다.");
        }
    }
}
