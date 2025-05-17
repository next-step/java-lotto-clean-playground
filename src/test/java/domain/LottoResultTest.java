package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class LottoResultTest {
    @Test
    void secondRankTest() {
        Lotto lotto1 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .toList());
        Lotto lotto2 = new Lotto(Stream.of(11, 12, 13, 14, 15,16)
                .map(LottoNumber::new)
                .toList());
        Lottos lottos = new Lottos(List.of(lotto1, lotto2));

        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Stream.of(1, 2, 3, 4, 5, 7)
                        .map(LottoNumber::new)
                        .toList()),
                new LottoNumber(6)
        );

        LottoResult lottoResult = new LottoResult(lottos, winningLotto);

        Map<LottoRank, Integer> result = lottoResult.getResult();
        assertThat(result.get(LottoRank.SECOND)).isEqualTo(1);
    }
}