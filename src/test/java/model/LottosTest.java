package model;

import global.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @DisplayName("우승 로또가 들어왔을 때 LottoResult를 생성한다.")
    @Test
    void get_lotto_result_with_winning_lotto() {
        // given
        final Lottos lottos = new Lottos(List.of(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), new Lotto(Arrays.asList(2, 3, 4, 5, 6, 7))));
        final Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        // when
        final LottoResult result = lottos.getResult(winningLotto);

        // then
        final Map<Rank, Integer> resultMap = result.getResult();
        assertThat(resultMap).containsEntry(Rank.FIRST_PLACE, 1);
        assertThat(resultMap).containsEntry(Rank.SECOND_PLACE, 1);
        assertThat(resultMap).containsEntry(Rank.THIRD_PLACE, 0);
        assertThat(resultMap).containsEntry(Rank.FOURTH_PLACE, 0);
        assertThat(resultMap).containsEntry(Rank.LAST_PLACE, 0);
    }
}
