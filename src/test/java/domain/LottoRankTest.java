package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LottoRankTest {

    @Test
    @DisplayName("로또 랭크 반환 메서드 테스트")
    void getLottoRank() {
        //given
        final FakeLottoNumberGenerator fakeLottoNumberGenerator = new FakeLottoNumberGenerator();
        final int lottoMoney = 3000;
        final Lottos lottos = new Lottos(fakeLottoNumberGenerator, lottoMoney);
        final List<Integer> lastWeekLottoNumber = List.of(1, 2, 3, 4, 5, 6);
        final LottoRank lottoRank = new LottoRank(lottos, lastWeekLottoNumber);
        final Map<String, Integer> expected = Map.of("3", 0, "4", 0, "5", 0, "6", 3);

        //when
        final Map<String, Integer> actual = lottoRank.getLottoRank();

        //then
        assertEquals(expected, actual);
    }
}
