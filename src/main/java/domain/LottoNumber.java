package domain;

public class LottoNumber {
    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
