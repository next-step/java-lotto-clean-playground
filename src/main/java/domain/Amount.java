package domain;

import constant.LottoConstant;

public class Amount {

    private final long value;

    public Amount(long value) {
        validateAmount(value);
        this.value = value;
    }

    private void validateAmount(long value) {
        if(value < LottoConstant.LOTTO_PRICE){
            throw new IllegalArgumentException(String.format("%d원 이상을 입력해주세요.", LottoConstant.LOTTO_PRICE));
        }
    }

    public long getValue() {
        return value;
    }
}
