package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LottosTest {
    @Test
    void 로또_번호_개수가_6개가_아니라면_예외가_발생한다() {
        //given
        List<LottoNumber> threeMatched = Stream.of(1, 2, 3, 45, 44, 43)
                .map(LottoNumber::valueOf)
                .toList();
        List<LottoNumber> fourMatched = Stream.of(1, 2, 3, 4, 45, 44)
                .map(LottoNumber::valueOf)
                .toList();
        List<LottoNumber> fiveMatched = Stream.of(1, 2, 3, 4, 5, 45)
                .map(LottoNumber::valueOf)
                .toList();
        List<LottoNumber> sixMatched = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::valueOf)
                .toList();
        List<Lotto> lottoList = List.of(
                new Lotto(threeMatched),
                new Lotto(fourMatched), new Lotto(fourMatched),
                new Lotto(fiveMatched), new Lotto(fiveMatched), new Lotto(fiveMatched),
                new Lotto(sixMatched), new Lotto(sixMatched), new Lotto(sixMatched), new Lotto(sixMatched)
        );
        Lottos lottos = new Lottos(lottoList);
        List<LottoNumber> winningNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::valueOf)
                .toList();

        //when
        Map<LottoRank, Integer> matchedResult = lottos.calculateMatchCounts(winningNumbers);

        //then
        assertAll(
                () -> assertThat(matchedResult.get(LottoRank.THREE_MATCHED)).isEqualTo(1),
                () -> assertThat(matchedResult.get(LottoRank.FOUR_MATCHED)).isEqualTo(2),
                () -> assertThat(matchedResult.get(LottoRank.FIVE_MATCHED)).isEqualTo(3),
                () -> assertThat(matchedResult.get(LottoRank.SIX_MATCHED)).isEqualTo(4)
        );
    }
}
