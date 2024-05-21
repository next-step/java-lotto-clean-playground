package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LastWeekLottoNumberTest {

    @Test
    @DisplayName("지난 주 로또 당첨 번호 입력 테스트")
    void getLastWeekLottoNumber() {
        //given
        final String inputLastWeekNumber = "1,2,3,4,5,6";
        final LastWeekLottoNumber lastWeekLottoNumber = new LastWeekLottoNumber(inputLastWeekNumber);
        final List<Integer> expected = lastWeekLottoNumber.getLastWeekLottoNumber();

        //when
        final List<Integer> actual = List.of(1, 2, 3, 4, 5, 6);

        //then
        assertEquals(expected, actual);
    }
}
