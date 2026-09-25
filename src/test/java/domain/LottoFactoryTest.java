package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {
    @Test
    void 정해진_번호로_생성한_로또의_당첨결과를_계산한다() {
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

        Lottos lottos = lottoFactory.create(1);

        Lotto winningLotto = new Lotto(
                List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );

        LottoResult result = lottos.getMatchCount(winningLotto);

        assertThat(result.getWinningCount(Rank.SIX))
                .isEqualTo(1);
    }
}