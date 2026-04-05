package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCalculatorTest {

    @DisplayName("기록된 통계를 바탕으로 정확한 수익률을 계산한다.")
    @Test
    void calculateYieldTest() {
        // given
        LottoCalculator calculator = new LottoCalculator();
        Money money = new Money(14000); // 14장 로또 구매

        // 5등(상금 5000원) 1번 당첨이라고 강제기록시키기
        calculator.valueAdd(Rank.THREE);

        // when
        double yield = calculator.calculateYield(money);

        // then: 5000 / 14000 소수점 둘째자리까지 계산시키기
        assertThat(yield).isBetween(0.35, 0.36);
    }
}