package domain;

public class LottoNumber {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final int lottoNumber;

    public LottoNumber(int number) {
        validateRange(number);
        lottoNumber = number;
    }

    private void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다. 잘못된 값: " + number);
        }

    }

    public int getLottoNumber(){
        return lottoNumber;
    }
}
