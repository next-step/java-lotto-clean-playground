package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRanksTest {

    @Test
    @DisplayName("맞은 개수에 해당하는 상금을 전부 더하여 총 상금을 구해야 한다")
    void testGetTotalPrize() {
        List<LottoRank> rankList = new ArrayList<>();
        rankList.add(LottoRank.FIFTH_PRIZE);
        rankList.add(LottoRank.FOURTH_PRIZE);
        rankList.add(LottoRank.SECOND_PRIZE);
        rankList.add(LottoRank.FIRST_PRIZE);
        LottoRanks testLottoRanks = LottoRanks.of(rankList);
        long expected = 5000 + 50000 + 30000000 + 2000000000;

        long actual = testLottoRanks.getTotalPrize();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("맞은 숫자 개수에 해당하는 로또의 개수를 세어 반환해야 한다")
    void testGetNumberOfRank() {
        List<LottoRank> rankList = new ArrayList<>();
        rankList.add(LottoRank.FOURTH_PRIZE);
        rankList.add(LottoRank.FOURTH_PRIZE);
        rankList.add(LottoRank.SECOND_PRIZE);
        rankList.add(LottoRank.FIRST_PRIZE);

        LottoRanks testLottoRanks = LottoRanks.of(rankList);

        assertSoftly(soft -> {
            soft.assertThat(testLottoRanks.getNumberOfRank(LottoRank.FOURTH_PRIZE)).isEqualTo(2);
            soft.assertThat(testLottoRanks.getNumberOfRank(LottoRank.SECOND_PRIZE)).isEqualTo(1);
            soft.assertThat(testLottoRanks.getNumberOfRank(LottoRank.FIRST_PRIZE)).isEqualTo(1);
        });
    }

}
