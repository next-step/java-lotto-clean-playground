package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTicketsTest {

    @Test
    @DisplayName("수동 로또 티켓이 정상적으로 생성되어야 한다.")
    void createManualTickets() {
        List<List<Integer>> manualNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(7, 8, 9, 10, 11, 12)
        );

        LottoTickets manualTickets = new LottoTickets(manualNumbers);

        assertEquals(2, manualTickets.getTickets().size(), "수동 로또 티켓 개수가 일치하지 않습니다.");
        assertEquals(manualNumbers, manualTickets.getFormattedTicketNumbers(), "수동 로또 번호가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("자동 로또 티켓이 정상적으로 생성되어야 한다.")
    void createAutoTickets() {
        int autoTicketCount = 3;

        LottoTickets autoTickets = new LottoTickets(autoTicketCount);

        assertEquals(autoTicketCount, autoTickets.getTickets().size(), "자동 로또 티켓 개수가 일치하지 않습니다.");
        autoTickets.getTickets().forEach(ticket ->
                assertEquals(6, ticket.getNumbers().size(), "자동 생성된 로또 번호 개수가 6개가 아닙니다.")
        );
    }

    @Test
    @DisplayName("수동 + 자동 로또 티켓이 정상적으로 합쳐져야 한다.")
    void mergeTickets() {
        List<List<Integer>> manualNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        LottoTickets manualTickets = new LottoTickets(manualNumbers);
        LottoTickets autoTickets = new LottoTickets(2);

        LottoTickets mergedTickets = LottoTickets.merge(manualTickets, autoTickets);

        assertEquals(3, mergedTickets.getTickets().size(), "병합된 티켓 개수가 맞지 않습니다.");
    }

    @Test
    @DisplayName("LottoTickets는 불변 객체여야 한다.")
    void ticketsShouldBeImmutable() {
        List<List<Integer>> manualNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        LottoTickets lottoTickets = new LottoTickets(manualNumbers);

        manualNumbers.get(0).set(0, 99);

        assertNotEquals(99, lottoTickets.getTickets().get(0).getNumbers().get(0),
                "LottoTickets 내부 데이터가 변경되었습니다. 불변성이 깨졌습니다.");
    }
}
