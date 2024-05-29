package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTest {

    @Test
    @DisplayName("로또 반환값 테스트")
    void getLottos() {
        //given
        final FakeLottoNumberGenerator fakeLottoNumberGenerator = new FakeLottoNumberGenerator();
        final Lotto lotto = new Lotto(fakeLottoNumberGenerator);
        final List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);

        //when
        final List<Integer> actual = lotto.getLottoNumber();

        //then
        assertEquals(expected, actual);
    }
}
