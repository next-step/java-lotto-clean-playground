package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static model.LottoConstraints.LOTTO_SIZE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumbersTest {

    private static final int BASIC_MINIMUM_LOTTO_NUMBER = 1;
    private static final String STRING_SEPARATOR = " ";

    @Test
    @DisplayName("LottoNumber 6개를 통해 인스턴스를 생성한다")
    void createBySixLottoNumber() {
        Set<LottoNumber> legalLottoNumberSet = getLegalLottoNumberSet();

        LottoNumbers lottoNumbers = new LottoNumbers(legalLottoNumberSet);
    }

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
        Set<LottoNumber> illegalLottoNumberSet = convertNumbersStringIntoLottoNumberSet(numbersString);

        assertThatThrownBy(() -> new LottoNumbers(illegalLottoNumberSet))
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
        Set<LottoNumber> illegalLottoNumberSet = convertNumbersStringIntoLottoNumberSet(numbersString);

        assertThatThrownBy(() -> new LottoNumbers(illegalLottoNumberSet))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Set<LottoNumber> getLegalLottoNumberSet() {
        int lottoNumberValue = BASIC_MINIMUM_LOTTO_NUMBER;
        HashSet<LottoNumber> lottoNumbers = new HashSet<>();

        while (isSmallThanMaxSize(lottoNumbers)) {
            LottoNumber lottoNumber = new LottoNumber(lottoNumberValue);
            lottoNumbers.add(lottoNumber);

            lottoNumberValue++;
        }

        return Collections.unmodifiableSet(lottoNumbers);
    }

    private boolean isSmallThanMaxSize(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() < LOTTO_SIZE;
    }

    private Set<LottoNumber> convertNumbersStringIntoLottoNumberSet(String numbersString) {
        String[] splitedNumbersString = numbersString.split(STRING_SEPARATOR);

        return Arrays.stream(splitedNumbersString)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}