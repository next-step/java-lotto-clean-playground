package domain.lotto;

import exception.DuplicatedLottoNumberException;
import exception.LottoNumberSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumbersTest {

    @DisplayName("로또 번호 6개로 LottoNumbers를 생성한다")
    @Test
    void createLottoNumbers() {
        // given
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );

        // when
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);

        // then
        assertThat(lottoNumbers.getLottoNumbers()).hasSize(6);
    }

    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    @Test
    void throwExceptionWhenSizeIsNot6() {
        // given
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
        );

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(numbers))
                .isInstanceOf(LottoNumberSizeException.class);
    }

    @DisplayName("중복된 번호가 있으면 예외가 발생한다")
    @Test
    void throwExceptionWhenDuplicated() {
        // given
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(1), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(numbers))
                .isInstanceOf(DuplicatedLottoNumberException.class);
    }

    @DisplayName("문자열로 LottoNumbers를 생성한다")
    @Test
    void createFromString() {
        // given
        String input = "1, 2, 3, 4, 5, 6";

        // when
        LottoNumbers lottoNumbers = LottoNumbers.from(input);

        // then
        assertThat(lottoNumbers.getLottoNumbers()).hasSize(6);
    }

    @DisplayName("LottoNumbers는 오름차순으로 정렬된다")
    @Test
    void sortedNumbers() {
        // given
        String input = "6, 5, 4, 3, 2, 1";

        // when
        LottoNumbers lottoNumbers = LottoNumbers.from(input);
        List<LottoNumber> numbers = lottoNumbers.getLottoNumbers();

        // then
        assertThat(numbers.get(0).getValue()).isEqualTo(1);
        assertThat(numbers.get(5).getValue()).isEqualTo(6);
    }

    @DisplayName("특정 번호가 포함되어 있는지 확인한다")
    @Test
    void containsNumber() {
        // given
        LottoNumbers lottoNumbers = LottoNumbers.from("1, 2, 3, 4, 5, 6");
        LottoNumber containedNumber = new LottoNumber(3);
        LottoNumber notContainedNumber = new LottoNumber(7);

        // when
        boolean containsThree = lottoNumbers.contains(containedNumber);
        boolean containsSeven = lottoNumbers.contains(notContainedNumber);

        // then
        assertThat(containsThree).isTrue();
        assertThat(containsSeven).isFalse();
    }

    @DisplayName("일치하는 번호 개수를 반환한다")
    @Test
    void countMatching() {
        // given
        LottoNumbers numbers1 = LottoNumbers.from("1, 2, 3, 4, 5, 6");
        LottoNumbers numbers2 = LottoNumbers.from("1, 2, 3, 7, 8, 9");

        // when
        int matchCount = numbers1.countMatching(numbers2);

        // then
        assertThat(matchCount).isEqualTo(3);
    }

    @DisplayName("toString은 [1, 2, 3, 4, 5, 6] 형식으로 반환한다")
    @Test
    void toStringFormat() {
        // given
        LottoNumbers lottoNumbers = LottoNumbers.from("1, 2, 3, 4, 5, 6");

        // when
        String result = lottoNumbers.toString();

        // then
        assertThat(result).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}
