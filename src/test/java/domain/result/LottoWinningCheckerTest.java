package domain.result;

import domain.*;
import domain.generator.FixedLottoGenerator;
import domain.generator.LottoGenerator;
import domain.util.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoWinningCheckerTest {


    @Test
    @DisplayName("1등, 2등, 5등 각각 1개씩 있는 경우 당첨 통계가 정확히 계산된다")
    void should_calculate_correct_prize_counts_for_various_match_cases() {
        // given
        Lotto firstPrize = InputParser.parseLotto("1,2,3,4,5,6");       // 6개 일치 → 1등
        Lotto secondPrize = InputParser.parseLotto("1,2,3,4,5,7");      // 5개 + 보너스 → 2등
        Lotto fifthPrize = InputParser.parseLotto("1,2,3,10,11,12");    // 3개 일치 → 5등

        List<Lotto> testLottos = List.of(firstPrize, secondPrize, fifthPrize);
        LottoGenerator fixedGenerator = new FixedLottoGenerator(testLottos);
        LottoManager manager = new LottoManager(fixedGenerator);
        Lottos lottos = manager.purchaseLottos(new Money(3000),0);

        Lotto winningLotto = InputParser.parseLotto("1,2,3,4,5,6");
        LottoNumber bonus = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonus);

        // when
        LottoResult result = LottoWinningChecker.checkLotto(lottos, winningNumbers);

        // then
        assertThat(result.firstPrizeCount()).isEqualTo(1);
        assertThat(result.secondPrizeCount()).isEqualTo(1);
        assertThat(result.thirdPrizeCount()).isEqualTo(0);
        assertThat(result.fourthPrizeCount()).isEqualTo(0);
        assertThat(result.fifthPrizeCount()).isEqualTo(1);
    }
}