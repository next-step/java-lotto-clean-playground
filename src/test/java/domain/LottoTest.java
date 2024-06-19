package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTest {

    @Test
    @DisplayName("자동 로또 반환값 테스트")
    void autoLotto() {
        // given
        final FakeLottoNumberGenerator fakeLottoNumberGenerator = new FakeLottoNumberGenerator();
        final Lotto lotto = new Lotto(fakeLottoNumberGenerator);
        final List<LottoNumber> expected = List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        // when
        final List<LottoNumber> actual = lotto.getLottoNumber();

        // then
        assertEquals(expected.size(), actual.size());
    }

    @Test
    @DisplayName("수동 로또 반환값 테스트")
    void passiveLotto() {
        // given
        final List<Integer> passiveLottoNumber = List.of(3, 4, 5, 6, 7, 8);
        final Lotto lotto = new Lotto(passiveLottoNumber);
        final List<LottoNumber> expected = List.of(new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6), new LottoNumber(7), new LottoNumber(8));


        // when
        final List<LottoNumber> actual = lotto.getLottoNumber();

        // then
        assertEquals(expected.size(), actual.size());
    }
}
