package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
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
                        .containsExactly(new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(4),
                                new LottoNumber(5),
                                new LottoNumber(6))
                );
    }

    @Test
    @DisplayName("생성된 로또 리스트가 수정 불가능해야 한다.")
    void shouldBeImmutableLottos() {
        // given
        int count = 1;
        LottoNumberGenerator generator = new FixNumberGenerator();

        Lottos lottos = Lottos.generate(count, generator);
        List<Lotto> values = lottos.getValues();

        // when & then
        assertThatThrownBy(() -> values.add(new Lotto(List.of(
                new LottoNumber(7),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10),
                new LottoNumber(11),
                new LottoNumber(12))
        )))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
