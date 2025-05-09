package domain;

import enums.LottoRank;
import enums.LottoType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class LottoResultTest {

    LottoResult lottoResult;
    Lottos lottos;

    @BeforeEach
    void beforeEach() {
        Lotto lotto1 = Lotto.from(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)), LottoType.MANUAL);
        Lotto lotto2 = Lotto.from(new ArrayList<>(List.of(2, 3, 4, 5, 6, 7)), LottoType.MANUAL);
        Lotto lotto3 = Lotto.from(new ArrayList<>(List.of(3, 4, 5, 6, 7, 8)), LottoType.MANUAL);

        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(lotto1);
        lottoList.add(lotto2);
        lottoList.add(lotto3);

        lottos = new Lottos(lottoList);
    }

    @Test
    @DisplayName("lottoResult가_로또의_맞춘숫자를_정확히_계산한다")
    void lottoResult가_로또의_맞춘숫자를_정확히_계산한다() {

        //given
        LottoWinningNumbers winningNumbers = LottoWinningNumbers.from(List.of(1, 2, 3, 4, 5, 6), 7);
        lottoResult = LottoResult.createLottoResult(winningNumbers, lottos);

        //when
        Map<LottoRank, Integer> resultByRank = lottoResult.getResultByRank();

        //then
        assertThat(resultByRank.get(LottoRank.NO_MATCH)).isEqualTo(0);
        assertThat(resultByRank.get(LottoRank.MATCH_3)).isEqualTo(0);
        assertThat(resultByRank.get(LottoRank.MATCH_4)).isEqualTo(1);
        assertThat(resultByRank.get(LottoRank.MATCH_5)).isEqualTo(0);
        assertThat(resultByRank.get(LottoRank.MATCH_5_BONUS)).isEqualTo(1);
        assertThat(resultByRank.get(LottoRank.MATCH_6)).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 리스트가 빈 경우 예외를 던진다")
    void 로또_리스트가_빈_경우_예외를_던진다() {

        //given
        LottoWinningNumbers winningNumbers = LottoWinningNumbers.from(List.of(1, 2, 3, 4, 5, 6), 7);

        assertThatThrownBy(() -> LottoResult.createLottoResult(winningNumbers, new Lottos(List.of())))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
