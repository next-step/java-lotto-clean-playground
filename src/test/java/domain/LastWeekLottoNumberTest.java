package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LastWeekLottoNumberTest {

    @Test
    void getLastWeekLottoNumber() {
        //given
        final String inputLastWeekNumber = "1,2,3,4,5,6";
        final LastWeekLottoNumber lastWeekLottoNumber = new LastWeekLottoNumber(inputLastWeekNumber);
        final List<Integer> expected = lastWeekLottoNumber.getLastWeekLottoNumber();

        //when
        final List<Integer> actual = List.of(1,2,3,4,5,6);

        //then
        assertEquals(expected, actual);
    }
}
