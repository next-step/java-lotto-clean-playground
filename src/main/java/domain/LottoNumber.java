package domain;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return String.valueOf(number);
    }
}
