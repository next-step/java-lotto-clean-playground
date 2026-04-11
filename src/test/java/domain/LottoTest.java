package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTest {

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Test
    @DisplayName("로또 번호의 개수가 6개가 아니면 예외가 발생한다.")
    void validateSizeTest() {
        List<LottoNumber> numbers = convertToLottoNumbers(List.of(1, 2, 3, 4, 5));

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자 개수가 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void validateDuplicateTest() {
        List<LottoNumber> numbers = convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 5));

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 로또 숫자가 존재합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void validateBonusNumberTest() {
        // given
        Lotto winnerNumbers = new Lotto(convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonusNumber = new LottoNumber(3);

        // when // then
        assertThatThrownBy(() -> Lotto.validateBonusNumber(winnerNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼의 숫자가 지난 주 당첨 번호와 중복됩니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3, 4, 5, 6, FIRST",
            "1, 2, 3, 4, 5, 7, SECOND",
            "1, 2, 3, 4, 5, 8, THIRD",
            "1, 2, 3, 4, 8, 9, FOURTH",
            "1, 2, 3, 8, 9, 10, FIFTH",
            "1, 2, 8, 9, 10, 11, MISS"
    })
    @DisplayName("당첨 번호와 보너스 번호를 비교하여 정확한 등수를 계산한다.")
    void calculateRankTest(int n1, int n2, int n3, int n4, int n5, int n6, Rank expectedRank) {
        Lotto myLotto = new Lotto(convertToLottoNumbers(List.of(n1, n2, n3, n4, n5, n6)));
        Lotto winnerNumbers = new Lotto(convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonusNumber = new LottoNumber(7);

        Rank actualRank = myLotto.calculateRank(winnerNumbers, bonusNumber);

        assertThat(actualRank).isEqualTo(expectedRank);
    }

    @Test
    @DisplayName("로또 번호가 특정 번호를 포함하고 있는지 확인한다.")
    void containsTest() {
        // given
        Lotto lotto = new Lotto(convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        // when // then
        assertThat(lotto.contains(new LottoNumber(1))).isTrue();
        assertThat(lotto.contains(new LottoNumber(45))).isFalse();
    }

    @Test
    @DisplayName("다른 로또와 비교하여 일치하는 번호의 개수를 반환한다.")
    void getMatchCountTest() {
        Lotto lotto1 = new Lotto(convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Lotto lotto2 = new Lotto(convertToLottoNumbers(List.of(4, 5, 6, 7, 8, 9)));

        int matchCount = lotto1.getMatchCount(lotto2);

        assertThat(matchCount).isEqualTo(3);
    }
}
