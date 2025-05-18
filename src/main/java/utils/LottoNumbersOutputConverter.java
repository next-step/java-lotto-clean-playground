package utils;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import dto.LottoNumbersDto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersOutputConverter {

    public static List<LottoNumbersDto> convert(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(LottoNumbersOutputConverter::convert)
                .collect(Collectors.toList());
    }

    private static LottoNumbersDto convert(Lotto lotto) {
        List<Integer> values = lotto.getNumbers().stream()
                .map(LottoNumber::value)
                .collect(Collectors.toList());

        return new LottoNumbersDto(values);
    }
}
