package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberParserTest {

    @Test
    @DisplayName("로또 번호 입력 테스트(보너스 볼 미포함)")
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

    @Test
    @DisplayName("올바르지 않은 값을 입력한 경우 테스트")
    void getLastWeekLottoNumber_generate_incorrect_exception(){
        //given
        final String lastWeekLottoNumber = "1,2,3,A,4,5";
        final var expected = "올바른 숫자 입력하세요.";

        //when
        final RuntimeException actual = assertThrows(RuntimeException.class, () -> new LottoNumberParser(lastWeekLottoNumber));

        //then
        assertEquals(expected, actual.getMessage());
    }

    @Test
    @DisplayName("로또 범위를 넘는 숫자를 입력하였을 경우 테스트")
    void getLastWeekLottoNumber_generate_range_exception(){
        //given
        final String lastWeekLottoNumber = "1,2,3,4,5,50";
        final var expected = "로또 범위 아님";

        //when
        final RuntimeException actual = assertThrows(RuntimeException.class, () -> new LottoNumberParser(lastWeekLottoNumber));

        //then
        assertEquals(expected, actual.getMessage());
    }
}
