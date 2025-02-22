package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.LottoNumbers;
import domain.Money;

public class Parser {

    private static final String DELIMITER = ", ";

    public static Money parseMoney(int money) {
        return new Money(money);
    }

    public static List<LottoNumbers> parseLottoNumbersList(List<String> lottoNumbersList) {
        return lottoNumbersList.stream()
            .map(Parser::parseLottoNumbers)
            .toList();
    }

    public static LottoNumbers parseLottoNumbers(String lottoNumbers) {
        return new LottoNumbers(Arrays.stream(lottoNumbers.split(DELIMITER))
            .map(Integer::parseInt)
            .collect(Collectors.toSet()));
    }
}
