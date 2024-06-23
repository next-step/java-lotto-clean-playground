package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottosTest {

    @Test
    @DisplayName("로또 묶음 사이즈 테스트")
    void getLottos() {
        // given
        final int lottoMoney = 3000;
        final String inputPassiveLottoNumber = "1,2,3,4,5,6";
        final LottoNumberParser lottoNumberParser = new LottoNumberParser(inputPassiveLottoNumber);
        final List<LottoNumberParser> lottoNumberParsers = List.of(lottoNumberParser);
        final CreateLottoNumber createLottoNumber = new LottoNumberGenerator();
        final Lottos lottos = new Lottos(createLottoNumber, lottoNumberParsers, lottoMoney);
        final int expected = 3;

        // when
        final int actual = lottos.getLottos().size();

        // then
        assertEquals(expected, actual);
    }
}
