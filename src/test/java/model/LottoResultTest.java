package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class LottoResultTest {

    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        lottoResult = new LottoResult(winningNumbers, bonusNumber);
    }

    @Test
    @DisplayName("로또 당첨 순위를 올바르게 계산하는 지 검증한다.")
    void should_return_correct_ranks() {

        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 10)),
                new Lotto(List.of(1, 2, 3, 4, 10, 11)),
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        );

        List<LottoRank> ranks = lottoResult.calculateRank(lottos);

        assertThat(ranks).containsExactly(
                LottoRank.SIX_MATCHES,
                LottoRank.FIVE_MATCHES_BONUS,
                LottoRank.FIVE_MATCHES,
                LottoRank.FOUR_MATCHES,
                LottoRank.THREE_MATCHES,
                LottoRank.NO_WINNER
        );
    }

    @Test
    @DisplayName("로또 번호가 3개 미만 일치하면 당첨자가 없다고 반환하는 지 검증한다.")
    void should_return_no_winner_if_under_three_matches() {

        List<Lotto> lottos = List.of(
                new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                new Lotto(List.of(1, 10, 11, 12, 13, 14)),
                new Lotto(List.of(1, 2, 10, 11, 12, 13))
        );

        List<LottoRank> ranks = lottoResult.calculateRank(lottos);

        assertThat(ranks).containsOnly(LottoRank.NO_WINNER);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호에 포함되면 예외를 던지는 지 검증한다.")
    void should_throw_exception_if_bonus_in_winning() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 3;

        assertThatThrownBy(() -> new LottoResult(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호 목록에 포함될 수 없습니다.");
    }
}
