package domain.model;

import domain.LottoConstants;

public record LottoNumber(int number) {
    public LottoNumber {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 값이어야 합니다.");
        }
    }
}
