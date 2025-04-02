package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    private static class TestLottoNumberGenerator implements LottoNumberGenerator {
        @Override
        public List<LottoNumber> generate() {
            return Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(6)
            );
        }
    }

    @Test
    @DisplayName("Lotto 객체 생성 시, 크기 6인 리스트가 생성되어야 한다.")
    void lotto_creation_test() {
        LottoNumberGenerator generator = new TestLottoNumberGenerator();
        Lotto lotto = new Lotto(generator);

        assertNotNull(lotto.getNumbers());
        assertEquals(6, lotto.getNumbers().size());
    }

    @Test
    @DisplayName("Lotto 객체의 번호가 정상적으로 생성되어야 한다.")
    void lotto_number_creation_test() {
        LottoNumberGenerator generator = new TestLottoNumberGenerator();
        Lotto lotto = new Lotto(generator);

        List<LottoNumber> expectedNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        assertEquals(expectedNumbers, lotto.getNumbers());
    }

    @Test
    @DisplayName("당첨 번호와 일치하는 개수를 올바르게 계산하여야 한다.")
    void test() {
        LottoNumberGenerator generator = new TestLottoNumberGenerator();
        Lotto lotto = new Lotto(generator);

        List<LottoNumber> winningNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(7),
                new LottoNumber(8),
                new LottoNumber(9)
        );

        int matchCount = lotto.calculateMatchCount(winningNumbers);

        assertEquals(3, matchCount);
    }

}