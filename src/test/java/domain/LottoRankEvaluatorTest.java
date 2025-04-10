package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankEvaluatorTest {
    @Test
    @DisplayName("일치한 당첨 번호 개수와 보너스 번호 일치 여부에 따라 알맞은 순위들을 저장해야한다.")
    void testLottoRankEvaluate() {
        Lottos lottos = Lottos.of(List.of(
                Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.of(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.of(List.of(1, 2, 3, 4, 5, 8)),
                Lotto.of(List.of(1, 2, 3, 4, 10, 11)),
                Lotto.of(List.of(1, 2, 3, 12, 13, 14)),
                Lotto.of(List.of(1, 2, 20, 21, 22, 23))
        ));

        Lotto winningNumbers = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.from(7);

        List<LottoRank> result = LottoRankEvaluator.evaluate(lottos, winningNumbers, bonusNumber);

        assertThat(result).containsExactly(
                LottoRank.FIRST_PRIZE,
                LottoRank.SECOND_PRIZE,
                LottoRank.THIRD_PRIZE,
                LottoRank.FOURTH_PRIZE,
                LottoRank.FIFTH_PRIZE,
                LottoRank.NO_PRIZE
        );
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호중 하나와 중복될 경우 예외가 발생해야 한다.")
    void testBonusNumberDuplicate() {
        Lottos lottos = Lottos.of(List.of(
                Lotto.of(List.of(1, 2, 3, 4, 5, 6))
        ));
        Lotto winningNumbers = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.from(6); // 중복된 번호

        assertThatThrownBy(() -> LottoRankEvaluator.evaluate(lottos, winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
