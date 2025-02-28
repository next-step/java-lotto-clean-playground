package view;

import domain.*;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class LottoInputViewTest {

    private LottoInputView lottoInputView;

    @BeforeEach
    void setUp() {
        lottoInputView = new LottoInputView();
    }

    @Test
    @DisplayName("구입 금액 입력 테스트")
    void shouldReturnPurchaseAmount() {
        String input = "14000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        lottoInputView = new LottoInputView();

        int purchaseAmount = lottoInputView.getPurchaseAmount();
        assertEquals(14000, purchaseAmount);
    }

    @Test
    @DisplayName("수동 로또 개수 입력 테스트")
    void shouldReturnManualLottoCount() {
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        LottoCount totalLottoCount = new LottoCount(5);

        lottoInputView = new LottoInputView();

        int manualLottoCount = lottoInputView.getManualLottoCount(totalLottoCount);
        assertEquals(3, manualLottoCount);
    }

    @Test
    @DisplayName("수동 로또 개수 초과 입력 시 예외 발생")
    void shouldThrowException_WhenManualCountExceedsTotal() {
        String input = "6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        LottoCount totalLottoCount = new LottoCount(5);

        lottoInputView = new LottoInputView();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                lottoInputView.getManualLottoCount(totalLottoCount)
        );

        assertEquals("수동 로또 개수는 0~5 사이여야 합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("보너스 볼 입력 테스트")
    void shouldReturnBonusBall() {
        String input = "7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        lottoInputView = new LottoInputView();

        BonusBall bonusBall = lottoInputView.inputBonusBall();

        assertEquals(7, bonusBall.getBonusBall());
    }

}
