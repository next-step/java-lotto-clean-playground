import domain.LottoCountCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("로또 개수 계산 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottoCountCalculatorTest {

    @Test
    @DisplayName("구매할 로또의 개수를 구한다.")
    public void calculateLottoCount() {
        //given
        LottoCountCalculator lottoCountCalculator = new LottoCountCalculator();
        final int buyingCosts = 14000;
        final int expected = 14;

        //when
        final int result = lottoCountCalculator.calculateLottoCount(buyingCosts);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
