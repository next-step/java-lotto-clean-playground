package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumbersTest {

    private static final String STRING_SEPARATOR = " ";

    @ParameterizedTest
    @DisplayName("LottoNumber의 개수가 6개 미만이면 예외가 발생한다")
    @CsvSource({
            "20",
            "1 45",
            "10 20 30",
            "11 22 33 44",
            "1 2 3 4 5"
    })
    void ifLottoNumberCountLessThanSixThenThrowException(String numbersString) {
        Set<LottoNumber> illegalLottoNumberCollection = convertNumbersStringIntoLottoNumberCollection(numbersString);

        assertThatThrownBy(() -> new LottoNumbers(illegalLottoNumberCollection))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("LottoNumber의 개수가 6개 초과면 예외가 발생한다")
    @CsvSource({
            "1 2 3 4 5 6 7",
            "10 11 20 21 30 31 40 41",
            "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15"
    })
    void ifLottoNumberCountBiggerThanSixThenThrowException(String numbersString) {
        Set<LottoNumber> illegalLottoNumberCollection = convertNumbersStringIntoLottoNumberCollection(numbersString);

        assertThatThrownBy(() -> new LottoNumbers(illegalLottoNumberCollection))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Set<LottoNumber> convertNumbersStringIntoLottoNumberCollection(String numbersString) {
        String[] splitedNumbersString = numbersString.split(STRING_SEPARATOR);

        return Arrays.stream(splitedNumbersString)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toUnmodifiableSet());
    }

}
