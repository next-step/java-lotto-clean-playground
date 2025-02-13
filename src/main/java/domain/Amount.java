package domain;

import constant.LottoConstant;

public class Amount {

    private long value;

    public Amount(long value) {
        validateAmount(value);
        this.value = value;
    }

    private void validateAmount(long value) {
        if(value < LottoConstant.LOTTO_PRICE){
            throw new IllegalArgumentException("천원 이상을 입력해주세요.");
        }
    }

    public long getValue() {
        return value;
    }
}
