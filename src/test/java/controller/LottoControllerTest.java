package controller;

import model.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoControllerTest {

    private LottoController lottoController;

    @BeforeEach
    void setUp() {
        lottoController = new LottoController();
    }

    @Test
    @DisplayName("구매 금액이 1000원 단위가 아니거나 1000원 미만인 경우 예외가 발생한다.")
    void validatePurchaseAmountTest() {
        Money validMoney = new Money(5000);
        Money invalidMoney1 = new Money(900);  // 1000원 미만
        Money invalidMoney2 = new Money(1500); // 1000원 단위가 아님

        assertDoesNotThrow(() -> lottoController.validatePurchaseAmount(validMoney)); // 정상 입력
        assertThrows(IllegalArgumentException.class, () -> lottoController.validatePurchaseAmount(invalidMoney1),
                "1000원 미만일 때 예외가 발생해야 합니다.");
        assertThrows(IllegalArgumentException.class, () -> lottoController.validatePurchaseAmount(invalidMoney2),
                "1000원 단위가 아닐 때 예외가 발생해야 합니다.");
    }

    @Test
    @DisplayName("수동 구매 개수가 전체 구매 가능 개수를 초과할 경우 예외가 발생한다.")
    void validateManualCountTest() {
        Money money = new Money(3000); // 총 3장 구매 가능
        int validManualCount = 2;
        int invalidManualCount = 5; // 초과

        assertDoesNotThrow(() -> lottoController.validateManualCount(validManualCount, money)); // 정상 입력
        assertThrows(IllegalArgumentException.class, () -> lottoController.validateManualCount(invalidManualCount, money),
                "수동 구매 개수가 초과되었을 때 예외가 발생해야 합니다.");
    }

    @Test
    @DisplayName("수동 및 자동 로또 티켓을 정상적으로 생성한다.")
    void generateLottoTicketsTest() {
        Money money = new Money(5000); // 총 5장 구매 가능
        int manualCount = 2;
        List<List<Integer>> manualNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(7, 8, 9, 10, 11, 12)
        );

        LottoTickets lottoTickets = new LottoTickets(manualNumbers, money.getTicketCount() - manualCount);

        assertEquals(5, lottoTickets.getTickets().size(), "로또 티켓 개수가 5장이 아닙니다.");
        assertEquals(manualNumbers.get(0), lottoTickets.getTickets().get(0).getSortedNumbers(), "첫 번째 수동 로또 번호가 다릅니다.");
        assertEquals(manualNumbers.get(1), lottoTickets.getTickets().get(1).getSortedNumbers(), "두 번째 수동 로또 번호가 다릅니다.");
    }

    @Test
    @DisplayName("로또 결과를 정상적으로 생성한다.")
    void createLottoResultTest() {
        List<Lotto> lottoTickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 8))  // 3등
        );

        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoResult lottoResult = new LottoResult(lottoTickets, winningNumbers);

        assertNotNull(lottoResult, "로또 결과 객체가 null입니다.");
        assertEquals(1, lottoResult.getMatchCountMap().get(Rank.FIRST), "1등 당첨 개수가 올바르지 않습니다.");
        assertEquals(1, lottoResult.getMatchCountMap().get(Rank.SECOND), "2등 당첨 개수가 올바르지 않습니다.");
        assertEquals(1, lottoResult.getMatchCountMap().get(Rank.THIRD), "3등 당첨 개수가 올바르지 않습니다.");
    }
}
