package utils;

import domain.Lottos;
import domain.Lotto;
import domain.LottoNumber;
import dto.LottoNumbersDto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersParser {

    public static List<LottoNumbersDto> parse(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(LottoNumbersParser::toDto)
                .collect(Collectors.toList());
    }

    private static LottoNumbersDto toDto(Lotto lotto) {
        List<Integer> values = lotto.getNumbers().getNumbers().stream()
                .map(LottoNumber::value)
                .collect(Collectors.toList());

        return new LottoNumbersDto(values);
    }
}
