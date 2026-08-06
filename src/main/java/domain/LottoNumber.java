package domain;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static LottoNumber from(int number) {
        validate(number);
        return new LottoNumber(number);
    }

    private static final int MIN = 1;
    private static final int MAX = 45;

    private static void validate(int number) {
        if (number < MIN || number > MAX)  {
            throw new IllegalArgumentException();
        }
    }
}
