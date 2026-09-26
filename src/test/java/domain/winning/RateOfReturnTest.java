package domain.winning;

import static org.assertj.core.api.Assertions.assertThat;
import domain.purchase.PurchasePrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateOfReturnTest {
    @Test
    @DisplayName("요구사항 예시와 같은 수익률을 계산한다.")
    void ratoeOfReturnExample() {
        RateOfReturn rateOfReturn = new RateOfReturn(5000, new PurchasePrice(14000));
        assertThat(rateOfReturn.getValue()).isEqualTo(0.35);
    }

    @Test
    @DisplayName("수익이 0이면 수익률은 0이다.")
    void RateOfReturnIsZeroWhenRevenueIsZero() {
        RateOfReturn rateOfReturn = new RateOfReturn(0, new PurchasePrice(1000));
        assertThat(rateOfReturn.getValue()).isEqualTo(0);
    }
}
