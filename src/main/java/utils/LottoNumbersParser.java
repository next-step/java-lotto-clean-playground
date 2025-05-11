package utils;

import domain.Lottos;
import dto.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersParser {

    public static List<LottoNumbers> parse(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(lotto -> new LottoNumbers(lotto.getNumbers()))
                .collect(Collectors.toList());
    }
}
