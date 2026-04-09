package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    void validateSizeTest() {
        //given
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6),
                new LottoNumber(7)
        );

        //when //then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자 개수가 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void validateDuplicateTest() {
        //given
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(5)
        );

        //when //then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 로또 숫자가 존재합니다.");
    }

    @Test
    @DisplayName("당첨 번호와 비교하여 일치하는 숫자의 개수를 반환한다.")
    void getMatchCountTest() {
        //given
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        Lotto winnerNumbers = new Lotto(List.of(
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6),
                new LottoNumber(7), new LottoNumber(8), new LottoNumber(9)
        ));

        //when
        int matchCount = lotto.getMatchCount(winnerNumbers);

        //then
        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    @DisplayName("특정 로또 번호가 포함되어 있는지 확인한다.")
    void containsTest() {
        //given
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoNumber target = new LottoNumber(3);

        //when
        boolean result = lotto.contains(target);

        //then
        assertThat(result).isTrue();
    }
}
