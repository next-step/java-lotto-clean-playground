package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoPurchaseInfoTest {

    @Test
    @DisplayName("LottoPurchaseInfo 객체가 정상적으로 생성되고 금액을 반환할 수 있다.")
    void createMoneyAndGetAmount() {
        int amount = 5000;

        LottoPurchaseInfo lottoPurchaseInfo = new LottoPurchaseInfo(amount);

        assertEquals(amount, lottoPurchaseInfo.getAmount(), "금액이 올바르게 저장되지 않았습니다.");
    }

    @Test
    @DisplayName("로또 구매 가능 개수를 정확히 반환한다.")
    void getCorrectTicketCount() {
        LottoPurchaseInfo lottoPurchaseInfo = new LottoPurchaseInfo(5000);

        int ticketCount = lottoPurchaseInfo.getTicketCount();

        assertEquals(5, ticketCount, "로또 티켓 개수가 잘못 계산되었습니다.");
    }

    @Test
    @DisplayName("구매 금액이 1000원 단위가 아니거나 1000원 미만인 경우 예외가 발생한다.")
    void validatePurchaseAmountTest() {
        assertThrows(IllegalArgumentException.class, () -> new LottoPurchaseInfo(900),
                "1000원 미만일 때 예외가 발생해야 합니다.");
        assertThrows(IllegalArgumentException.class, () -> new LottoPurchaseInfo(1500),
                "1000원 단위가 아닐 때 예외가 발생해야 합니다.");
        assertThrows(IllegalArgumentException.class, () -> new LottoPurchaseInfo(0),
                "0원일 때 예외가 발생해야 합니다.");
    }
}
