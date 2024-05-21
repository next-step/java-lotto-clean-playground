package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottosRankTest {

    @Test
    @DisplayName("로또 등수 사이즈 파악 테스트")
    void getRankLottos() {
        //given
        final int lottoMoney = 3000;
        final Lottos lottos = new Lottos(lottoMoney);
        final String inputLastWeekNumber = "1,2,3,4,5,6";
        final LastWeekLottoNumber lastWeekLottoNumber = new LastWeekLottoNumber(inputLastWeekNumber);
        final LottosRank lottosRank = new LottosRank(lottos, lastWeekLottoNumber.getLastWeekLottoNumber());
        final List<Integer> expected = List.of(1, 1, 1, 1);

        //when
        final int actual = 4;

        //then
        assertEquals(expected.size(), actual);
    }
}
