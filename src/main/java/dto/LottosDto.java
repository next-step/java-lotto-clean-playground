package dto;

import model.LottoNumbers;
import model.Lottos;

import java.util.List;

public class LottosDto {

    private final List<String> lottoStrings;

    public LottosDto(Lottos lottos) {
        List<LottoNumbers> lottoNumbersCollection = lottos.getLottoNumbersCollection();

        this.lottoStrings = lottoNumbersCollection.stream()
                .map(LottoNumbers::toString)
                .toList();
    }

    public List<String> getValues() {
        return List.copyOf(lottoStrings);
    }

}
