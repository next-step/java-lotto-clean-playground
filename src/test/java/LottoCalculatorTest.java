import domain.LottoCalculator;
import domain.WinningLotto;
import domain.WinningLottoCount;
import domain.WinningLottosStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("로또 계산 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottoCalculatorTest {

    @Test
    @DisplayName("자동 구매할 로또의 개수를 구한다.")
    public void calculateLottoCount() {
        //given
        LottoCalculator lottoCalculator = new LottoCalculator();
        final int buyingCosts = 14000;
        final int expected = 12;
        final int passiveLottoCount = 2;

        //when
        final int result = lottoCalculator.calculateAutoLottoCount(buyingCosts, passiveLottoCount);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 금액과 구입 금액으로 수익률을 계산할 수 있다.")
    public void calculateRateOfReturn() {
        //given
        final int buyingCosts = 5000;
        final WinningLottoCount winningLottoCount = new WinningLottoCount();
        winningLottoCount.updateWinningLottoCountStatus(WinningLottosStatus.THREE_CORRECT_LOTTOS);
        LottoCalculator lottoCalculator = new LottoCalculator();
        final double expected = (double) WinningLottosStatus.THREE_CORRECT_LOTTOS.getPrizeMoney() / buyingCosts;

        //when
        final double result = lottoCalculator.calculateRateOfReturn(buyingCosts, winningLottoCount);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
