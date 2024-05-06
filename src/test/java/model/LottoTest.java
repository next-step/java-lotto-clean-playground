package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTest {

    @Nested
    class 로또_생성_시 {

        @Test
        void 로또는_중복되지_않는_6개의_숫자로_이루어져있다() {
            // given
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

            // when & then
            assertThatCode(() -> Lotto.from(numbers)).doesNotThrowAnyException();
        }

        @Test
        void 로또는_6개의_숫자로_이루어져야_한다() {
            // given
            List<Integer> numbers = List.of(1, 2, 3, 4, 5);

            // when & then
            assertThatThrownBy(() -> Lotto.from(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또는 6개의 숫자로 이루어져야 합니다.");
        }

        @Test
        void 로또는_중복되지_않는_6개의_숫자로_이루어져야_한다() {
            // given
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

            // when & then
            assertThatThrownBy(() -> Lotto.from(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또는 중복되지 않는 6개의 숫자로 이루어져야 합니다.");
        }

        @Test
        void 로또의_숫자들은_오름차순으로_정렬되어_있다() {
            // given
            List<Integer> numbers = List.of(6, 5, 4, 3, 2, 1);

            // when
            var lotto = Lotto.from(numbers);

            // then
            assertThat(lotto.numbers()).isSorted();
        }
    }
}
