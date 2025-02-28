package dto;

import model.LottoNumbers;
import model.Lottos;

import java.util.List;

public class LottosDto {

    private final List<String> lottoStrings;

    private LottosDto(List<String> lottoStrings) {
        this.lottoStrings = lottoStrings;
    }

    public static LottosDto from(Lottos lottos) {
        List<LottoNumbers> lottoNumbersList = lottos.getLottoNumbersList();

        List<String> lottoStrings = lottoNumbersList.stream()
                .map(LottoNumbers::toString)
                .toList();

        return new LottosDto(lottoStrings);
    }

    public List<String> getValues() {
        return List.copyOf(lottoStrings);
    }

}
