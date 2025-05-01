package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrizeTest {

    @Test
    @DisplayName("3개 번호 일치하면 5등 당첨")
    void Fifth_Prize_Test() {
        Prize prize = Prize.of(3, false);
        assertEquals(Prize.FIFTH_PRIZE, prize);
    }

    @Test
    @DisplayName("4개 번호 일치하면 4등 당첨")
    void Fourth_Prize_Test() {
        Prize prize = Prize.of(4, false);
        assertEquals(Prize.FOURTH_PRIZE, prize);
    }

    @Test
    @DisplayName("5개 번호 일치하면 3등 당첨")
    void Third_Prize_Test() {
        Prize prize = Prize.of(5, false);
        assertEquals(Prize.THIRD_PRIZE, prize);
    }

    @Test
    @DisplayName("5개 번호 + 보너스 번호 일치하면 2등 당첨")
    void Second_Prize_Test() {
        Prize prize = Prize.of(5, true);
        assertEquals(Prize.SECOND_PRIZE, prize);
    }

    @Test
    @DisplayName("6개 번호 일치하면 1등 당첨")
    void First_Prize_Test() {
        Prize prize = Prize.of(6, false);
        assertEquals(Prize.FIRST_PRIZE, prize);
    }

    @Test
    @DisplayName("일치하는 번호가 없으면 당첨 없음")
    void None_Test() {
        Prize prize = Prize.of(2, false);
        assertEquals(Prize.NONE, prize);
    }
}
