package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoListTest {

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
    @DisplayName("로또 구매 금액이 1000원 이상이고 1000원 단위일 경우 정상적으로 로또가 생성된다.")
    void purchaseAmount_test1() {
        LottoList lottoList = new LottoList(5000, new TestLottoNumberGenerator());
        assertNotNull(lottoList);
        assertEquals(5, lottoList.getLottoCount());  // 5000원으로 5개의 로또가 생성되어야 함
    }

    @Test
    @DisplayName("로또 구매 금액이 1000원 미만이라면 예외가 발생한다.")
    void purchaseAmount_test2() {
        assertThrows(IllegalArgumentException.class, () -> new LottoList(999, new TestLottoNumberGenerator()));
    }

    @Test
    @DisplayName("로또 구매 금액이 1000원 단위가 아니라면 예외가 발생한다.")
    void purchaseAmount_test3() {
        assertThrows(IllegalArgumentException.class, () -> new LottoList(1500, new TestLottoNumberGenerator()));
    }

    @Test
    @DisplayName("로또 리스트가 구매 금액에 따른 개수만큼 생성되어야 한다.")
    void lottoList_creation_test() {
        LottoList lottoList = new LottoList(5000, new TestLottoNumberGenerator());

        assertEquals(5, lottoList.getLottoCount());
        assertEquals(5, lottoList.getLottoList().size());
    }

    @Test
    @DisplayName("로또 상금이 올바르게 계산되어야 한다.")
    void calculate_prize_test() {
        // [1, 2, 3, 4, 5, 6] 한 장 구매
        LottoList lottoList = new LottoList(1000, new TestLottoNumberGenerator());
        // 1, 2, 3 => 당첨 개수 : 3개
        List<LottoNumber> winningNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(7),
                new LottoNumber(8),
                new LottoNumber(9)
        );

        int prize = lottoList.calculatePrize(winningNumbers);
        //당첨 개수 : 3개 => 상금 5000원
        assertEquals(5000, prize);
    }
}