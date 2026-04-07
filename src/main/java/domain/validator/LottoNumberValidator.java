package domain.validator;

public class LottoNumberValidator {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    public static void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
}