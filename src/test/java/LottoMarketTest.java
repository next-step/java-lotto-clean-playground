import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoMarketTest {
    private LottoMarket market;

    @BeforeEach
    void setUp() {
        market = new LottoMarket();
    }

    @Test
    void 당첨번호_정상동작_테스트() {
        List<Integer> winningNumbers = Arrays.asList(3, 12, 19, 25, 32, 41);
        market.setWinningNumbers(winningNumbers);
        assertEquals(winningNumbers, market.getWinningNumbers());
    }

    @Test
    void 당첨번호_개수_오류_테스트() {
        List<Integer> wrongNumbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(RuntimeException.class, () -> market.setWinningNumbers(wrongNumbers));
    }

    @Test
    void 랜덤_로또_생성_테스트() {
        market.randomLotto();
        assertEquals(1, market.getLottos().size());
    }

    @Test
    void 수동_로또_생성_테스트() {
        List<Integer> manualLotto = Arrays.asList(1, 2, 3, 4, 5, 6);
        market.manualLotto(manualLotto);
        assertEquals(1, market.getManualLottos().size());
    }
}

