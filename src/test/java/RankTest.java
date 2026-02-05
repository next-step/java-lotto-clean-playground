import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    @DisplayName("5개 일치 + 보너스 일치면 SECOND(2등) 반환")
    void returnsSecondWhenFiveAndBonusMatched() {
        Optional<Rank> rank = Rank.from(5, true);

        assertThat(rank).contains(Rank.BONUS);
    }

    @Test
    @DisplayName("5개 일치 + 보너스 불일치면 FIVE(3등) 반환")
    void returnsFiveWhenFiveAndBonusNotMatched() {
        Optional<Rank> rank = Rank.from(5, false);

        assertThat(rank).contains(Rank.FIVE);
    }

    @Test
    @DisplayName("6개 일치면 SIX(1등) 반환")
    void returnsSixWhenSixMatched() {
        Optional<Rank> rank = Rank.from(6, false);

        assertThat(rank).contains(Rank.SIX);
    }

    @Test
    @DisplayName("2개 이하면 등수가 없으므로 empty 반환")
    void returnsEmptyWhenUnderThreeMatched() {
        Optional<Rank> rank = Rank.from(2, false);

        assertThat(rank).isEmpty();
    }
}
