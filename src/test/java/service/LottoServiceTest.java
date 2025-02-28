package service;

import domain.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LottoServiceTest {

    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
    }

    @Test
    @DisplayName("구매 금액이 1000원 미만일 때 예외 발생")
    void shouldThrowException_WhenPurchaseAmountIsLessThanMinimum() {
        Amount invalidAmount = new Amount(500);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                lottoService.validatePurchaseAmount(invalidAmount)
        );

        assertEquals("최소 구매 금액은 1000원 입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("구매 금액이 1000원 단위가 아닐 때 예외 발생")
    void shouldThrowException_WhenPurchaseAmountIsNotMultipleOfLottoPrice() {
        Amount invalidAmount = new Amount(1050);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                lottoService.validatePurchaseAmount(invalidAmount)
        );

        assertEquals("구입 금액 단위는 1000원 입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("로또 개수 계산")
    void shouldCalculateCorrectLottoCount() {
        Amount amount = new Amount(14000);
        LottoCount lottoCount = lottoService.calculateLottoAmount(amount);
        assertEquals(14, lottoCount.getCount());
    }

    @Test
    @DisplayName("자동으로 생성된 로또 개수가 요청한 개수와 동일해야한다")
    void shouldCreateCorrectNumberOfLottos() {
        LottoCount lottoCount = new LottoCount(5);
        Lottos lottos = lottoService.createLottos(lottoCount);

        assertNotNull(lottos);
        assertEquals(5, lottos.getLottos().size());
    }

    @Test
    @DisplayName("각 로또는 6개의 숫자를 포함해야한다")
    void eachLottoShouldContainSixNumbers() {
        LottoCount lottoCount = new LottoCount(3);
        Lottos lottos = lottoService.createLottos(lottoCount);

        for (Lotto lotto : lottos.getLottos()) {
            assertEquals(6, lotto.getLottoNumbers().size());
        }
    }
}
