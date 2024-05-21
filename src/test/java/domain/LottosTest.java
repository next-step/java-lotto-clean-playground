package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottosTest {
    @Test
    @DisplayName("로또 묶음 사이즈 테스트")
    void getLottos() {
        //given
        final int lottoMoney = 3000;
        final CreateLottoNumber createLottoNumber = new LottoNumberGenerator();
        final Lottos lottos = new Lottos(lottoMoney, createLottoNumber);
        final int expected = 3;

        //when
        final int actual = lottos.getLottos().size();

        //then
        assertEquals(expected, actual);
    }
}
