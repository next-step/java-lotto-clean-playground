package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import lotto.domain.model.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTest {

    @Test
    @DisplayName("일치하는 개수와 보너스 볼 여부에 따라 올바른 등수를 반환한다.")
    void valueOf_Returns_Correct_Rank() {
        assertAll(
            // 1등: 6개 일치
            () -> assertThat(LottoRank.valueOf(6, false)).isEqualTo(LottoRank.FIRST),

            // 2등: 5개 일치 + 보너스 일치
            () -> assertThat(LottoRank.valueOf(5, true)).isEqualTo(LottoRank.SECOND),

            // 3등: 5개 일치 + 보너스 불일치
            () -> assertThat(LottoRank.valueOf(5, false)).isEqualTo(LottoRank.THIRD),

            // 4등: 4개 일치
            () -> assertThat(LottoRank.valueOf(4, false)).isEqualTo(LottoRank.FOURTH),

            // 꽝: 2개 이하 일치
            () -> assertThat(LottoRank.valueOf(2, false)).isEqualTo(LottoRank.MISS)
        );
    }
}
