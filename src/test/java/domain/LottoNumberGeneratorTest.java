package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberGeneratorTest {

    @Test
    @DisplayName("로또 숫자 생성 인터페이스 테스트")
    void getRandomLottoNumber() {
        // given
        final CreateLottoNumber createLottoNumber = new FakeLottoNumberGenerator();
        final List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);

        // when
        final List<Integer> actual = createLottoNumber.getRandomLottoNumber();

        // then
        assertEquals(expected, actual);
    }
}
