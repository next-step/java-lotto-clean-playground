package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTicketsTest {

    @Test
    @DisplayName("수동으로 2장의 로또 티켓을 생성할 수 있다.")
    void createManualLottoTickets() {
        List<List<Integer>> manualNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(7, 8, 9, 10, 11, 12)
        );

        LottoTickets lottoTickets = new LottoTickets(manualNumbers, 0);
        List<Lotto> tickets = lottoTickets.getTickets();

        assertEquals(2, tickets.size(), "수동 로또 티켓 개수가 2장이 아닙니다.");
        for (int i = 0; i < manualNumbers.size(); i++) {
            assertEquals(manualNumbers.get(i), tickets.get(i).getSortedNumbers(), "수동 입력 로또 번호가 다릅니다.");
        }
    }

    @Test
    @DisplayName("자동으로 3장의 로또 티켓을 생성할 수 있다.")
    void createAutoLottoTickets() {
        int autoTicketCount = 3;

        LottoTickets lottoTickets = new LottoTickets(List.of(), autoTicketCount);
        List<Lotto> tickets = lottoTickets.getTickets();

        assertEquals(3, tickets.size(), "자동 로또 티켓 개수가 3장이 아닙니다.");
        for (Lotto ticket : tickets) {
            assertNotNull(ticket, "자동 생성된 로또 티켓이 null입니다.");
            assertEquals(6, ticket.getSortedNumbers().size(), "자동 생성된 로또 번호 개수가 6개가 아닙니다.");
        }
    }

    @Test
    @DisplayName("총 5장의 로또 티켓을 생성할 수 있다 (수동 2장 + 자동 3장).")
    void createFiveLottoTickets() {
        List<List<Integer>> manualNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(7, 8, 9, 10, 11, 12)
        );
        int autoTicketCount = 3;

        LottoTickets lottoTickets = new LottoTickets(manualNumbers, autoTicketCount);
        List<Lotto> tickets = lottoTickets.getTickets();

        assertEquals(5, tickets.size(), "총 로또 티켓 개수가 5장이 아닙니다.");
    }
}
