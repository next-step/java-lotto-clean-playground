package domain;

import static constant.LottoConstant.MAX_LOTTO_NUMBER;
import static constant.LottoConstant.MIN_LOTTO_NUMBER;

public class LottoNumber {

    private final Integer number;

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(int number) {
        if(number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이 값을 사용해야합니다.");
        }
    }

    public int getNumber() {
        return number;
    }
}
