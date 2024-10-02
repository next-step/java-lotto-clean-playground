import domain.WinningLottos;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("로또 당첨 개수 체크 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class WinningLottosTest {

    @Test
    @DisplayName("4개가 일치하는 로또의 개수가 하나 증가한다.")
    public void convertStringToInteger() {
        //given
        WinningLottos winningLottos = WinningLottos.FOUR_CORRECT_LOTTOS;
        final int expected = 1;

        //when
        winningLottos.addWinnerLotto();

        //then
        Assertions.assertThat(WinningLottos.FOUR_CORRECT_LOTTOS.getLottoCount()).isEqualTo(expected);
    }

    @DisplayName("일치 개수에 따른 로또 모음을 가져올 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "3, false, THREE_CORRECT_LOTTOS",
            "4, false, FOUR_CORRECT_LOTTOS",
            "5, false, FIVE_CORRECT_LOTTOS",
            "5, true, FIVE_AND_BONUS_CORRECT_LOTTOS",
            "6, false, SIX_CORRECT_LOTTOS",
    })
    private void convertStringToInteger(final int correctCount, final boolean isSecondPrize, final WinningLottos expected) {
        //given, when, then
        Assertions.assertThat(WinningLottos.of(correctCount, isSecondPrize)).isEqualTo(expected);
    }
}
