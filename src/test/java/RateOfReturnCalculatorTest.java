import domain.RateOfReturnCalculator;
import domain.WinningLottosStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("총 수익률 계산 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RateOfReturnCalculatorTest {

    @Test
    @DisplayName("총 금액과 구입 금액으로 수익률을 계산할 수 있다.")
    public void calculateRateOfReturn() {
        //given
        final int buyingCosts = 5000;
        WinningLottosStatus.THREE_CORRECT_LOTTOS.addWinnerLotto();
        RateOfReturnCalculator rateOfReturnCalculator = new RateOfReturnCalculator();
        final double expected = (double) WinningLottosStatus.THREE_CORRECT_LOTTOS.getPrizeMoney() / (double) buyingCosts;

        //when
        final double result = rateOfReturnCalculator.calculateRateOfReturn(buyingCosts);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
