package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottosTest {

    @Nested
    @DisplayName("로또 묶음 생성 테스트")
    class createLottos {

        @ParameterizedTest(name = "input으로 들어온 로또 묶음 내의 로또 개수가 {1}이라면 총 {1}개의 로또가 만들어진다.")
        @ValueSource(ints = {1, 10, 100})
        @DisplayName("주어진 개수만큼 로또를 생성한다.")
        void lottosSizeTest(int numberOfLotto) {
            // given
            NumberGenerator numberGenerator = new RandomLottoNumberGenerator();
            // when
            Lottos lottos = new Lottos(numberGenerator, numberOfLotto);
            // then
            assertThat(lottos.getSize())
                .isEqualTo(numberOfLotto);
        }
    }
}
