package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {
    @Test
    void 요청한_개수만큼_로또를_생성한다() {
        // given
        NumberGenerator numberGenerator = new FixedNumberGenerator(
                List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );
        LottoFactory lottoFactory = new LottoFactory(numberGenerator);

        // when
        Lottos lottos = lottoFactory.create(5);

        // then
        assertThat(lottos.getLottos()).hasSize(5);
    }
}
