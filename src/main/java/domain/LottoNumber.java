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
        return new LottoNumber(number);
    }
}
