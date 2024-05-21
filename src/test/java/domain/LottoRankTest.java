package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoRankTest {

    @Test
    @DisplayName("지난 수 로또 번호와 맞은 개수 테스트")
    void getSameLottoNumber() {
        //given
        final String inputLastWeekNumber = "1,2,3,4,5,6";
        final LastWeekLottoNumber lastWeekLottoNumber = new LastWeekLottoNumber(inputLastWeekNumber);
        final CreateLottoNumber createLottoNumber = new FakeLottoNumberGenerator();
        final Lotto lotto = new Lotto(createLottoNumber);
        final List<Integer> lastWeeklottoNumber = lastWeekLottoNumber.getLastWeekLottoNumber();
        final LottoRank lottoRank = new LottoRank(lotto, lastWeeklottoNumber);
        final int expected = lottoRank.getSameLottoNumber();

        //when
        final int actual = 6;

        //then
        assertEquals(expected, actual);
    }
}
