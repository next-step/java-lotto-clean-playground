package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LottosTest {
    @Test
    @DisplayName("로또 묶음 사이즈 테스트")
    void getLottos() {
        //given
        final Random randomNumberGenerator = new Random();
        final int lottoMoney = 3000;
        final CreateLottoNumber createLottoNumber = new LottoNumberGenerator(randomNumberGenerator);
        final Lottos lottos = new Lottos(createLottoNumber, lottoMoney);
        final int expected = 3;

        //when
        final int actual = lottos.getLottos().size();

        //then
        assertEquals(expected, actual);
    }
}
