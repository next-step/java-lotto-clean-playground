package dto;

import domain.*;
import java.util.List;

public class LottoDto {

    private List<Integer> lottoNumbers;

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public static LottoDto from(Lotto lotto) {
        LottoDto lottoDto = new LottoDto();

        lottoDto.lottoNumbers = lotto.getLottoNumbers().stream()
                .map(LottoNumber::getValue)
                .toList();

        return lottoDto;
    }

}
