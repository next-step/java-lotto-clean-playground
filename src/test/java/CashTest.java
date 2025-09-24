import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashTest {
    @Test
    @DisplayName("금액 입력 시 올바른 로또 갯수가 출력되는지 확인")

    void testCashToTicket(){
        Money money = new Money(5000);
        LottoTicketCount ticketNumber = MoneyToTicket.MoneyToTicket(money);
       assertEquals(5,ticketNumber.getCount());
    }
    // 1000의 단위가 아닌 금액 입력시 예외처리 로직 짠 후 테스트 코드 작성

    @Test
    @DisplayName("최종 당첨 금액 합계가 올바른지 확인")
    void totalSum(){
        MatchCount matchCount = new MatchCount();
        matchCount.addMatch3Count(2);
        matchCount.addMatch4Count(1);
        matchCount.addMatch5Count(0);
        matchCount.addMatch6Count(1);

        int sum = LottoProfit.LottoSum(matchCount);

        assertEquals(2000060000, sum);
    }

    @Test
    @DisplayName("수익률이 올바른지 확인")
    void profitRate(){
        int totalSum = 48000000;
        int purchaseAmount = 24000;
        double profitRate = LottoProfit.LottoProfit(purchaseAmount, totalSum);
        assertEquals(2000.0, profitRate);
    }
}
