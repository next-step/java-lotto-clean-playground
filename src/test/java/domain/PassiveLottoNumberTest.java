package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassiveLottoNumberTest {

    @Test
    @DisplayName("수동 로또 번호 묶음 테스트")
    void getPassiveLottoNumbers() {
        //given
        final String firstPassiveLottoNumber = "1,2,3,4,5,6";
        final String secondPassiveLottoNumber = "3,4,5,6,7,8";
        final PassiveLottoNumber passiveLottoNumber = new PassiveLottoNumber(List.of(firstPassiveLottoNumber, secondPassiveLottoNumber));
        final int expected = 2;

        //when
        final int actual = passiveLottoNumber.getPassiveLottoNumbers().size();

        //then
        assertEquals(expected, actual);
    }
}
