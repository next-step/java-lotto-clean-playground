package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberGeneratorTest {

    @Test
    @DisplayName("로또 숫자 생성 인터페이스 테스트")
    void getRandomLottoNumber() {
        //given
        final FakeLottoNumberGenerator fakeLottoNumberGenerator = new FakeLottoNumberGenerator();
        final List<Integer> expected = fakeLottoNumberGenerator.getRandomLottoNumber();

        //when
        final List<Integer> actual = List.of(1,2,3,4,5,6);

        //then
        assertEquals(expected, actual);
    }
}
