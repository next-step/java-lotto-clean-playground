package dto;

import domain.*;
import java.util.List;

public record LottoDto(List<Integer> lottoNumbers) {

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public static LottoDto from(Lotto lotto) {
        return new LottoDto(
                lotto.getLottoNumbers().stream()
                        .map(LottoNumber::getValue)
                        .toList()
        );
    }
}
