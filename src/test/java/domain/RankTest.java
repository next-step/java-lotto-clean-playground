package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {
    @Test
    @DisplayName("Enum을 구하는 정적 팩터리 메서드에서 두 객체가 같은 값인지 확인한다")
    void returnSameInstanceWhenStaticFactoryMethod() {
        List<Rank> first = Rank.getValues();
        List<Rank> second = Rank.getValues();

        assertThat(first == second).isTrue();
    }

    @Test
    @DisplayName("matchCount와 isBonusMatch에 따라 적절한 Rank를 반환한다")
    void return_correct_rank_based_on_input() {
        assertThat(Rank.from(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.from(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.from(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.from(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.from(3, false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("일치하는 Rank가 없으면 Rank.NONE을 반환한다")
    void return_none_when_no_matching_rank_found() {
        assertThat(Rank.from(2, false)).isEqualTo(Rank.NONE);
        assertThat(Rank.from(0, true)).isEqualTo(Rank.NONE);
    }

    @Test
    @DisplayName("각 Rank는 정확한 상금 정보를 가진다")
    void each_rank_has_correct_prize() {
        assertThat(Rank.FIRST.getPrize().getAmount()).isEqualTo(2_000_000_000L);
        assertThat(Rank.SECOND.getPrize().getAmount()).isEqualTo(1_500_000L);
        assertThat(Rank.THIRD.getPrize().getAmount()).isEqualTo(50_000L);
        assertThat(Rank.FOURTH.getPrize().getAmount()).isEqualTo(5_000L);
        assertThat(Rank.FIFTH.getPrize().getAmount()).isEqualTo(5_000L);
        assertThat(Rank.NONE.getPrize().getAmount()).isEqualTo(0L);
    }

}
