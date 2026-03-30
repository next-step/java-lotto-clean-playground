package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LottosTest {
    @Test
    void 로또_번호_개수가_6개가_아니라면_예외가_발생한다() {
        //given
        List<Integer> threeMatched = List.of(1, 2, 3, 45, 44, 43);
        List<Integer> fourMatched = List.of(1, 2, 3, 4, 45, 44);
        List<Integer> fiveMatched = List.of(1, 2, 3, 4, 5, 45);
        List<Integer> sixMatched = List.of(1, 2, 3, 4, 5, 6);
        List<Lotto> lottoList = List.of(
                new Lotto(threeMatched),
                new Lotto(fourMatched), new Lotto(fourMatched),
                new Lotto(fiveMatched), new Lotto(fiveMatched), new Lotto(fiveMatched),
                new Lotto(sixMatched), new Lotto(sixMatched), new Lotto(sixMatched), new Lotto(sixMatched)
        );
        Lottos lottos = new Lottos(lottoList);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

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
