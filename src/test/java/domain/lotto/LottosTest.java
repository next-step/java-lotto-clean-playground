package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static support.LottoTestHelper.lotto;
import static support.LottoTestHelper.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.FixNumberGenerator;
import strategy.LottoNumberGenerator;

class LottosTest {

    @Test
    @DisplayName("주어진 개수만큼 정확한 수의 로또를 생성한다.")
    void shouldCreateCountOfLottos() {
        // given
        int count = 5;
        LottoNumberGenerator generator = new FixNumberGenerator();

        // when
        Lottos lottos = Lottos.generate(count, generator);

        // then
        assertThat(lottos.getValues())
                .hasSize(count)
                .allSatisfy(lotto -> assertThat(lotto.getNumbers())
                        .containsExactlyElementsOf(numbers(1, 2, 3, 4, 5, 6)));
    }

    @Test
    @DisplayName("생성된 로또 리스트가 수정 불가능해야 한다.")
    void shouldBeImmutableLottos() {
        // given
        int count = 1;
        LottoNumberGenerator generator = new FixNumberGenerator();
        Lottos lottos = Lottos.generate(count, generator);

        // when & then
        assertThatThrownBy(() -> lottos.getValues().add(lotto(7, 8, 9, 10, 11, 12)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
