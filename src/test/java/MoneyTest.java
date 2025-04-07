import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.Money;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    void divideByUnit_검증() {
        Money money = new Money(8000);
        assertEquals(8, money.divideByUnit());
    }

    @Test
    void calculateRate_검증() {
        Money money = new Money(1000);
        assertEquals(1.5, money.calculateRate(1500), 0.001);
    }

    @Test
    void 최소금액_미만이면_예외처리한다() {
        assertThrows(IllegalArgumentException.class,
                () -> new Money(500));
    }
}
