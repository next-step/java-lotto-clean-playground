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
        // given
        List<LottoNumber> numbers = convertToLottoNumbers(List.of(1, 2, 3, 4, 5));

        // when // then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자 개수가 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void validateDuplicateTest() {
        // given
        List<LottoNumber> numbers = convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 5));

        // when // then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 로또 숫자가 존재합니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3, 4, 5, 6, FIRST",   // 6개 일치
            "1, 2, 3, 4, 5, 7, SECOND",  // 5개 일치 + 보너스 일치
            "1, 2, 3, 4, 5, 8, THIRD",   // 5개 일치
            "1, 2, 3, 4, 8, 9, FOURTH",  // 4개 일치
            "1, 2, 3, 8, 9, 10, FIFTH",  // 3개 일치
            "1, 2, 8, 9, 10, 11, MISS"   // 2개 일치 (꽝)
    })
    @DisplayName("당첨 번호와 보너스 번호를 비교하여 정확한 등수를 계산한다.")
    void calculateRankTest(int n1, int n2, int n3, int n4, int n5, int n6, Rank expectedRank) {
        // given
        Lotto myLotto = new Lotto(convertToLottoNumbers(List.of(n1, n2, n3, n4, n5, n6)));
        Lotto winnerNumbers = new Lotto(convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonusNumber = new LottoNumber(7);

        // when
        Rank actualRank = myLotto.calculateRank(winnerNumbers, bonusNumber);

        // then
        assertThat(actualRank).isEqualTo(expectedRank);
    }

    @Test
    @DisplayName("로또 번호가 특정 번호를 포함하고 있는지 확인한다.")
    void containsTest() {
        // given
        Lotto lotto = new Lotto(convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber target = new LottoNumber(3);
        LottoNumber nonTarget = new LottoNumber(45);

        // when // then
        assertThat(lotto.contains(target)).isTrue();
        assertThat(lotto.contains(nonTarget)).isFalse();
    }

    @Test
    @DisplayName("다른 로또와 비교하여 일치하는 번호의 개수를 반환한다.")
    void getMatchCountTest() {
        // given
        Lotto lotto1 = new Lotto(convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Lotto lotto2 = new Lotto(convertToLottoNumbers(List.of(4, 5, 6, 7, 8, 9)));

        // when
        int matchCount = lotto1.getMatchCount(lotto2);

        // then
        assertThat(matchCount).isEqualTo(3); // 4, 5, 6 일치
    }
}
