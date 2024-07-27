package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottosTest {

    @Nested
    @DisplayName("로또 묶음 생성 테스트")
    class createLottos {

        private static Stream<Arguments> methodSourceOfCreateLottos() {
            return Stream.of(
                Arguments.arguments(
                    List.of(
                        Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6)),
                        Lotto.from(Arrays.asList(1, 3, 5, 7, 9, 11)),
                       Lotto.from(Arrays.asList(45, 44, 43, 42, 41, 40)))
                    , 3)
            );
        }

        @ParameterizedTest(name = "{0}의 로또 묶음이 주어지면 총 lottos의 사이즈는 {1}이다.")
        @MethodSource("methodSourceOfCreateLottos")
        @DisplayName("lottos에 lotto를 추가할 수 있다.")
        void addLottoTest(List<Lotto> bunchOfLotto, int expectedLottosSize) {
            // given
            Lottos lottos = new Lottos();
            // when
            for (Lotto lotto : bunchOfLotto) {
                lottos.addLotto(lotto);
            }
            // then
            assertThat(lottos.getSize())
                .isEqualTo(expectedLottosSize);
        }

    }
}
