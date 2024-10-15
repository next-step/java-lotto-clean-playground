package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketsTest {

    private  LottoTickets lottoTickets;

    @BeforeEach
    public void setUp() {
        lottoTickets = new LottoTickets();
    }

    @Test
    @DisplayName("빈 티켓을 추가하면 예외가 발생한다.")
    public void testThrowExceptionIfAddNullTicket(){
        assertThatThrownBy(()->lottoTickets.addTicket(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("티켓을 추가하고 티켓의 수를 정확히 반환한다.")
    public void testReturnCorrectTicketCount(){

        LottoTicket lottoTicket1 = new LottoTicket(Arrays.asList(
                new LottoNumber(1),new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoTicket lottoTicket2 = new LottoTicket(Arrays.asList(
                new LottoNumber(7),new LottoNumber(8), new LottoNumber(9),
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)
        ));

        lottoTickets.addTicket(lottoTicket1);
        lottoTickets.addTicket(lottoTicket2);

        assertThat(lottoTickets.ticketsCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("자동 로또 티켓을 지정된 개수만큼 생성한다.")
    public void testGenerateAutomaticTickets() {
        int ticketCount = 5;
        LottoTickets automaticTickets = LottoTickets.generateAutomaticTickets(ticketCount);

        assertThat(automaticTickets.ticketsCount()).isEqualTo(ticketCount);
    }

    @Test
    @DisplayName("수동 로또 티켓과 자동 로또 티켓을 병합한다.")
    public void testMergeManualAndAutomaticTickets() {
        LottoTickets manualTickets = new LottoTickets();
        manualTickets.addTicket(LottoTicket.generateAutomaticNumbers());

        LottoTickets automaticTickets = LottoTickets.generateAutomaticTickets(3);

        lottoTickets.generateLottoTickets(manualTickets, automaticTickets);

        assertThat(lottoTickets.ticketsCount()).isEqualTo(4);
    }

    @Test
    @DisplayName("당첨 로또 티켓을 확인하고 결과를 업데이트 한다.")
    public void testCheckWinningTickets() {
        LottoTicket winningNumbers = LottoTicket.generateAutomaticNumbers();
        LottoNumber bonusNumber = new LottoNumber(45);

        LottoTicket ticket1 = LottoTicket.generateAutomaticNumbers();
        LottoTicket ticket2 = LottoTicket.generateAutomaticNumbers();

        lottoTickets.addTicket(ticket1);
        lottoTickets.addTicket(ticket2);

        Map<LottoRank, Integer> winningResult = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            winningResult.put(rank, 0);
        }

        lottoTickets.checkWinningTickets(winningResult, winningNumbers, bonusNumber);

        int totalWins = winningResult.values().stream().mapToInt(Integer::intValue).sum();
        assertThat(totalWins).isEqualTo(2);
    }

}
