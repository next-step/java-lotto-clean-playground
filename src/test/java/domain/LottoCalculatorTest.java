package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCalculatorTest {

    @DisplayName("2등 당첨을 포함하여 기록된 통계를 바탕으로 정확한 수익률을 계산한다.")
    @Test
    void calculateYieldTest() {
        // given
        LottoCalculator calculator = new LottoCalculator();
        Money money = new Money(10000); // 10장 구매 (10,000원 지출)

        // 2등(상금 30,000,000원) 1번 당첨 기록
        calculator.valueAdd(Rank.SECOND);

        // when
        double yield = calculator.calculateYield(money);

        // 30,000,000 / 10,000 = 3000.0
        assertThat(yield).isEqualTo(3000.0);
    }
}