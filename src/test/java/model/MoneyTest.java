package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    @DisplayName("Money 객체가 정상적으로 생성되고 금액을 반환할 수 있다.")
    void createMoneyAndGetAmount() {
        int amount = 5000;

        Money money = new Money(amount);

        assertEquals(amount, money.getAmount(), "금액이 올바르게 저장되지 않았습니다.");
    }

    @Test
    @DisplayName("로또 구매 가능 개수를 정확히 반환한다.")
    void getCorrectTicketCount() {
        Money money = new Money(5000);

        int ticketCount = money.getTicketCount();

        assertEquals(5, ticketCount, "로또 티켓 개수가 잘못 계산되었습니다.");
    }

    @Test
    @DisplayName("금액이 0원일 때 로또 티켓을 구매할 수 없다.")
    void zeroMoneyShouldReturnZeroTickets() {
        Money money = new Money(0);

        int ticketCount = money.getTicketCount();

        assertEquals(0, ticketCount, "금액이 0원일 때 티켓 개수는 0이어야 합니다.");
    }

    @Test
    @DisplayName("로또 한 장도 못 사는 금액일 때 티켓 개수는 0이어야 한다.")
    void lessThanLottoPriceShouldReturnZeroTickets() {
        Money money = new Money(500); // 로또 한 장 가격(1000)보다 적은 금액

        int ticketCount = money.getTicketCount();

        assertEquals(0, ticketCount, "로또 가격보다 적은 금액으로 티켓 개수가 0이어야 합니다.");
    }
}
