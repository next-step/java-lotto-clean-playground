import model.LottoRank;
import model.LottoResult;
import model.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoResultTest {

    @Test
    @DisplayName("")
    public void calculateTest() {
        // given
        List<Integer> winningNumbers = List.of(1,2,3,4,5,6);
        int bonusNumber = 7;

        LottoTicket ticket1 = new LottoTicket(List.of(1,2,3,4,5,6)); // 6개 일치 → 1등
        LottoTicket ticket2 = new LottoTicket(List.of(1,2,3,4,5,7)); // 5 + 보너스 → 2등
        LottoTicket ticket3 = new LottoTicket(List.of(1,2,3,4,5,8)); // 5개 → 3등
        LottoTicket ticket4 = new LottoTicket(List.of(1,2,3,4,8,9)); // 4개 → 4등
        LottoTicket ticket5 = new LottoTicket(List.of(1,2,3,8,9,10)); // 3개 → 5등
        LottoTicket ticket6 = new LottoTicket(List.of(8,9,10,11,12,13)); // 꽝

        List<LottoTicket> tickets = List.of(
                ticket1, ticket2, ticket3, ticket4, ticket5, ticket6
        );
        LottoResult lottoResult=new LottoResult();
        // when
        lottoResult.calculate(tickets, winningNumbers, bonusNumber);

        // then
        Map<LottoRank, Integer> result = lottoResult.getResult();

        assertThat(result.get(LottoRank.SIX)).isEqualTo(1);
        assertThat(result.get(LottoRank.FIVE_BONUS)).isEqualTo(1);
        assertThat(result.get(LottoRank.FIVE)).isEqualTo(1);
        assertThat(result.get(LottoRank.FOUR)).isEqualTo(1);
        assertThat(result.get(LottoRank.THREE)).isEqualTo(1);
    }

}
