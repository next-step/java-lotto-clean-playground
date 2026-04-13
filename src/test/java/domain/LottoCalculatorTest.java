package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoCalculatorTest {

    @Test
    @DisplayName("특정 등수의 결과 카운트를 1씩 증가시킨다.")
    void valueAddTest() {
        //given
        LottoCalculator calculator = new LottoCalculator();
        Rank rank = Rank.THIRD;

        //when
        calculator.valueAdd(rank);

        //then
        Map<Rank, Integer> result = calculator.getResult();
        assertThat(result.get(rank)).isEqualTo(1);
    }

    @Test
    @DisplayName("총 당첨 금액을 구입 금액으로 나누어 수익률을 계산한다.")
    void calculateYieldTest() {
        //given
        LottoCalculator calculator = new LottoCalculator();
        Money money = new Money(10000);
        calculator.valueAdd(Rank.FIFTH);

        //when
        double yield = calculator.calculateYield(money);

        //then
        assertThat(yield).isEqualTo(0.5);
    }

    @Test
    @DisplayName("초기화 시 모든 등수의 카운트는 0이다.")
    void initTest() {
        //given
        LottoCalculator calculator = new LottoCalculator();

        //when
        Map<Rank, Integer> result = calculator.getResult();

        //then
        assertThat(result.values()).allMatch(count -> count == 0);
        assertThat(result).hasSize(Rank.values().length);
    }
}
