package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        // given
        Lotto lotto1 = new Lotto(convertToLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Lotto lotto2 = new Lotto(convertToLottoNumbers(List.of(4, 5, 6, 7, 8, 9)));

        // when
        int matchCount = lotto1.getMatchCount(lotto2);

        // then
        assertThat(matchCount).isEqualTo(3);
    }
}
