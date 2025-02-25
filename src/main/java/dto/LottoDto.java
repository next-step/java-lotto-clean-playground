package dto;

import model.Lotto;
import model.LottoNumber;

import java.util.List;

public class LottoDto {

    private List<Integer> lottoNumbers;

    public static LottoDto from(Lotto lotto) {
        LottoDto lottoDto = new LottoDto();

        lottoDto.lottoNumbers = lotto.getLottoNumbers().stream()
                .map(LottoNumber::getValue)
                .toList();

        return lottoDto;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

}
