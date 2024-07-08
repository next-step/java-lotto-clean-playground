package model;

public record LottoNumber(int number) {

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
