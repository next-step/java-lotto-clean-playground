package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("구매 금액이 1000원 단위일 때 LottoPurchaseInfo가 정상 생성되어야 한다.")
    void validPurchaseAmountShouldCreateLottoPurchaseInfo() {
        int validAmount = 5000;

        LottoPurchaseInfo purchaseInfo = new LottoPurchaseInfo(validAmount);

        assertEquals(validAmount, purchaseInfo.getAmount(), "구매 금액이 일치하지 않습니다.");
        assertEquals(5, purchaseInfo.getTicketCount(), "구매 가능한 티켓 수가 올바르지 않습니다.");
    }

    @Test
    @DisplayName("수동 로또 개수가 구매 가능한 개수를 초과하면 예외 발생")
    void validateManualCountThrowsException() {
        LottoPurchaseInfo purchaseInfo = new LottoPurchaseInfo(5000);
        int manualCount = 6;

        assertThrows(IllegalArgumentException.class, () ->
                        lottoController.validateManualCount(manualCount, purchaseInfo),
                "수동 구매 개수가 초과했는데 예외가 발생하지 않았습니다."
        );
    }

    @Test
    @DisplayName("수동 및 자동 로또가 정상적으로 생성되고 합쳐져야 한다.")
    void generateLottoTicketsShouldWorkCorrectly() {
        LottoPurchaseInfo purchaseInfo = new LottoPurchaseInfo(5000);
        int manualCount = 2;
        List<List<Integer>> manualNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(7, 8, 9, 10, 11, 12)
        );

        LottoTickets manualTickets = new LottoTickets(manualNumbers);
        LottoTickets autoTickets = new LottoTickets(purchaseInfo.getTicketCount() - manualCount);
        LottoTickets lottoTickets = LottoTickets.merge(manualTickets, autoTickets);

        assertEquals(5, lottoTickets.getTickets().size(), "전체 로또 티켓 개수가 올바르지 않습니다.");
        assertEquals(manualNumbers, lottoTickets.getFormattedTicketNumbers().subList(0, 2),
                "수동 로또 번호가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("당첨 결과가 정상적으로 계산되어야 한다.")
    void processWinningResultsShouldCalculateCorrectly() {
        List<List<Integer>> manualNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        LottoTickets manualTickets = new LottoTickets(manualNumbers);
        LottoTickets autoTickets = new LottoTickets(2);
        LottoTickets lottoTickets = LottoTickets.merge(manualTickets, autoTickets);

        WinningNumbers winningNumbers = new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        LottoPurchaseInfo purchaseInfo = new LottoPurchaseInfo(5000);

        LottoResult lottoResult = new LottoResult(lottoTickets.getTickets(), winningNumbers);

        assertEquals(1, lottoResult.getMatchCountMap().getOrDefault(Rank.FIRST, 0),
                "6개 일치(1등) 당첨 개수가 맞지 않습니다.");
        assertTrue(lottoResult.calculateProfitRate(purchaseInfo.getAmount()) > 1,
                "수익률이 올바르게 계산되지 않았습니다.");
    }
}
