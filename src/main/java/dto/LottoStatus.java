package dto;

import domain.Lotto;
import domain.LottoNumber;

import java.util.List;

public record LottoStatus(List<LottoNumber> lottoNumbers) {
    public static LottoStatus from(Lotto lotto) {
        return new LottoStatus(lotto.getLottoNumber());
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
