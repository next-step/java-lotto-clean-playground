package utils;

import domain.LottoNumber;
import domain.LottoNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersInputParser {
    private static final String DELIMITER = ",";

    public static LottoNumbers parse(String input) {
        List<LottoNumber> numbers = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new LottoNumbers(numbers);
    }
}
