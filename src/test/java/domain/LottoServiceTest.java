package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    private final int LOTTO_PRICE = 1000;
    private final int FIRST_LOTTO_POSITION = 0;
    private final int LAST_LOTTO_POSITION = 6;
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;

    @Test
    @DisplayName("로또 티켓 수를 구한다.")
    public void makeLottoTicketCountTest() {
        //given
        int expectedMoney = 12000;
        int expectedLottoTicketCount = (int) expectedMoney / LOTTO_PRICE;

        //when
        int lottoTicketCount = new LottoService().makeLottoTicketCount(expectedMoney);

        //then
        assertThat(expectedLottoTicketCount).isEqualTo(lottoTicketCount);
    }

    @Test
    @DisplayName("로또 번호로 가능한 리스트를 구한다.")
    public void makeLottoNumberListTest() {
        //given
        List<Integer> expectedlottoNumberList = new ArrayList<>();

        for(int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            expectedlottoNumberList.add(i);
        }

        //when
        List<Integer> lottoNumberList = new LottoService().makeLottoNumberList();

        //then
        assertThat(expectedlottoNumberList).isEqualTo(lottoNumberList);
    }

    @Test
    @DisplayName("구매한 로또 번호들을 구한다. - 셔플 기능을 이용하기 때문에 같은 값이 나오기 어렵다.")
    public void makeLottoTickets() {
        //given
        int lottoTicketCount = new LottoService().makeLottoTicketCount(12000);
        List<Integer> lottoNumberList = new LottoService().makeLottoNumberList();

        List<List<Integer>> expectedLottoTickets = new ArrayList<>();

        List<Integer> lottoTicket;

        for(int i = 0;i < lottoTicketCount; i++) {
            Collections.shuffle(lottoNumberList);

            lottoTicket = lottoNumberList.subList(FIRST_LOTTO_POSITION, LAST_LOTTO_POSITION);
            Collections.sort(lottoTicket);
            expectedLottoTickets.add(lottoTicket.stream().toList());
        }

        //when
        List<List<Integer>> lottoTickets = new LottoService().makeLottoTickets(lottoTicketCount, lottoNumberList);

        //then
        assertThat(expectedLottoTickets).isEqualTo(lottoTickets);
    }
}
