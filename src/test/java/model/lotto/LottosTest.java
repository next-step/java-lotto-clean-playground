package model.lotto;

import model.dice.Dice;
import model.dice.FakeDice;
import model.lotto.exception.MoneyFormatException;
import model.lotto.exception.MoneyRemainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottosTest {

    private Dice dice;

    @BeforeEach
    void init() {
        dice = new FakeDice();
    }

    @Test
    void 천원_단위_로또_생성_정상() {
        // given
        String moneyInput = "14000";

        // when & then
        assertDoesNotThrow(() -> Lottos.createWith(dice, moneyInput));
    }

    @Test
    void 생성된_로또_갯수_계산() {
        // given
        String moneyInput = "14000";
        int expectSize = 14;

        // when
        Lottos lottos = Lottos.createWith(dice, moneyInput);

        // then
        assertThat(lottos.getLottosSize()).isEqualTo(expectSize);
    }

    @Test
    void 생성된_로또_목록_확인() {
        // given
        String moneyInput = "1000";
        Lottos lottos = Lottos.createWith(dice, moneyInput);
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<List<Integer>> totalLottoNumbers = lottos.getTotalLottoNumbers();

        // then
        assertSoftly(softly -> {
            softly.assertThat(totalLottoNumbers.size()).isEqualTo(1);
            softly.assertThat(totalLottoNumbers.get(0)).isEqualTo(expectedNumbers);
        });
    }

    @Nested
    class 로또들_생성_예외 {

        @Test
        void 천원_단위가_아니면_예외가_발생한다() {
            // given
            String moneyInput = "1234";

            // when & then
            assertThatThrownBy(() -> Lottos.createWith(dice, moneyInput))
                    .isInstanceOf(MoneyRemainException.class);
        }

        @ValueSource(strings = {"12abc", "-12000", "0"})
        @ParameterizedTest(name = "입력 값이 [{0}]인 경우")
        void 숫자가_아닌_문자가_섞이거나_음수라면_예외가_발생한다(final String moneyInput) {
            // when & then
            assertThatThrownBy(() -> Lottos.createWith(dice, moneyInput))
                    .isInstanceOf(MoneyFormatException.class);
        }
    }
}
