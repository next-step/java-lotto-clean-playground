package domain;

import static domain.Price.PRICE_OF_ONE_LOTTO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTest {
    @DisplayName("입력 개수만큼 로또 티켓을 발급한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 10})
    public void testLotto(int expected) {
        // given
        LottoTicketGenerator lottoTicketGenerator = new RandomLottoTicketGenerator();
        Price price = new Price(expected * PRICE_OF_ONE_LOTTO);

        // when
        Lotto lotto = new Lotto(price, lottoTicketGenerator, new ArrayList<>());
        int actual = lotto.getNumberOfTickets();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("티켓 리스트를 당첨 티켓과 비교하여 LottoResult를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"10,0", "10,5", "10,10"})
    public void testGetResults(int totalTicketCount, int manualTicketCount) {
        // given
        LottoTicket winnerTicket = new LottoTicket(new ArrayList<>(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::valueOf)
                        .toList()
        ));

        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        List<List<Integer>> manualTickets = Stream.generate(() -> List.of(8, 9, 10, 11, 12, 13))
                .limit(manualTicketCount)
                .toList();

        LottoTicketGenerator lottoTicketGenerator = new TestLottoTicketGenerator(winnerTicket);
        Price price = new Price(totalTicketCount * PRICE_OF_ONE_LOTTO);
        Lotto lotto = new Lotto(price, lottoTicketGenerator, manualTickets);

        List<LottoRank> lottoRankOfEachTicket = Stream.concat(
                Stream.generate(() -> LottoRank.MISS).limit(manualTicketCount),
                Stream.generate(() -> LottoRank.FIRST).limit(totalTicketCount - manualTicketCount)
        ).collect(Collectors.toList());

        // when
        LottoResult actual = lotto.getResults(winnerTicket, bonusNumber);
        LottoResult expected = new LottoResult(lottoRankOfEachTicket);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
