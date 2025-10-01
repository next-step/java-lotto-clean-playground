import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CashTest {
    @Test
    @DisplayName("금액 입력 시 올바른 로또 갯수가 출력되는지 확인")
    void testCashToTicket() {
        Money money = new Money(5500);
        LottoTicketCount ticketNumber = Money.getTicketCount(money);
        assertEquals(5, ticketNumber.getCount());
    }

    @Test
    @DisplayName("금액 입력이 올바르지 않을 시 예외처리")
    void testInvalidCash() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-5000));
    }

    @Test
    @DisplayName("최종 당첨 금액 합계가 올바른지 확인")
    void totalSum() {
        MatchCount matchCount = new MatchCount();
        matchCount.addCount(LottoPrice.MATCH_3, 2);
        matchCount.addCount(LottoPrice.MATCH_4, 1);
        matchCount.addCount(LottoPrice.MATCH_5, 1);
        matchCount.addCount(LottoPrice.MATCH_6, 1);


        long expectedSum = LottoPrice.MATCH_3.getPrice() * 2 +
                LottoPrice.MATCH_4.getPrice() * 1 +
                LottoPrice.MATCH_5.getPrice() * 1 +
                LottoPrice.MATCH_6.getPrice() * 1;

        LottoTotalPrice totalPrice = new LottoTotalPrice(matchCount);
        long sum = totalPrice.getTotalSum();

        assertEquals(expectedSum, sum);
    }

    @Test
    @DisplayName("수익률이 올바른지 확인")
    void profitRate() {
        final int totalSumValue = 48000000;
        final int purchaseAmount = 24000;

        // 1. ProfitRate의 의존성 Money 생성
        Money money = new Money(purchaseAmount);

        // 2. totalSumValue (48,000,000)을 반환하는 가상의 LottoTotalPrice 객체 생성
        //    익명 클래스를 사용하여 테스트 목적으로만 getTotalSum() 메서드를 오버라이드합니다.
        LottoTotalPrice fixedTotalPrice = new LottoTotalPrice(new MatchCount()) {
            @Override
            public long getTotalSum() {
                // 고정된 테스트 값 반환
                return (long) totalSumValue;
            }
        };

        // 3. ProfitRate 객체 생성 및 검증
        ProfitRate profitRate = new ProfitRate(money, fixedTotalPrice);

        // LottoProfit.LottoProfit(purchaseAmount, totalSum) 대신 ProfitRate.getProfitRate() 사용
        // 고정된 값으로 계산한 예상 값과 실제 ProfitRate 객체의 결과가 일치하는지 확인합니다.
        assertEquals((double) totalSumValue / purchaseAmount, profitRate.getProfitRate());
    }
}
