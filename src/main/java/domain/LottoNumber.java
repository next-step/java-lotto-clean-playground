package domain;

public class LottoNumber {

    private static final int FIRST_LOTTO_NUMBER = 1;
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final String EXCEPTION_LOTTO_RANGE = "로또 범위 아님";

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateLottoNumberRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    private void validateLottoNumberRange(int realLottoNumber) {
        if (realLottoNumber < FIRST_LOTTO_NUMBER || realLottoNumber > LAST_LOTTO_NUMBER) {
            throw new RuntimeException(EXCEPTION_LOTTO_RANGE);
        }
    }
}
