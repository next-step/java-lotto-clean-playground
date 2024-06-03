package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberParserTest {

    @Test
    @DisplayName("로또 번호 입력 테스트")
    void getLastWeekLottoNumber() {
        //given
        final String inputLastWeekNumber = "1,2,3,4,5,6";
        final LottoNumberParser lottoNumberParser = new LottoNumberParser(inputLastWeekNumber);
        final List<Integer> expected = lottoNumberParser.getRealLottoNumber();

        //when
        final List<Integer> actual = List.of(1, 2, 3, 4, 5, 6);

        //then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("로또 번호 입력 테스트 (보너스 볼 포함")
    void getLastWeekLottoNumber_contain_bonusBall() {
        //given
        final String inputLastWeekNumber = "1,2,3,4,5,6";
        final int bonusBall = 7;
        final LottoNumberParser lottoNumberParser = new LottoNumberParser(inputLastWeekNumber);
        lottoNumberParser.addBonusBall(bonusBall);
        final List<Integer> expected = lottoNumberParser.getRealLottoNumber();

        //when
        final List<Integer> actual = List.of(1, 2, 3, 4, 5, 6, 7);

        //then
        assertEquals(expected, actual);
    }
}
