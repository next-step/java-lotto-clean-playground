package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void lottoSizeTest() {
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6),
                new LottoNumber(7) // 7개!
        );

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개여야 합니다");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void lottoDuplicateTest() {
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1), new LottoNumber(1), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 로또 숫자");
    }

    @DisplayName("당첨 번호와 비교하여 일치하는 번호의 개수를 정확히 반환한다.")
    @Test
    void getMatchNumbersTest() {
        // given (내 로또: 1, 2, 3, 4, 5, 6)
        Lotto myLotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        // given (당첨 로또: 1, 2, 3, 10, 11, 12) -> 3개 일치해야 함
        Lotto winningLotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)
        ));

        // when
        int matchCount = myLotto.getMatchNumbers(winningLotto);

        // then
        assertThat(matchCount).isEqualTo(3);
    }
}