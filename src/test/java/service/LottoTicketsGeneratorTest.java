package service;

import model.LottoNumber;
import model.LottoTicket;
import model.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketsGeneratorTest {
    @Test
    @DisplayName("수동 로또 티켓과 자동 로또 티켓을 통해 티켓 묶음을 생성한다.")
    void generateTickets_ConsistOfManualTicketAndAutomaticTicket(){
        LottoTicket manualTicket1 = new LottoTicket(Arrays.asList(
                new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),
                new LottoNumber(4),new LottoNumber(5),new LottoNumber(6)));

        LottoTicket manualTicket2 = new LottoTicket(Arrays.asList(
                new LottoNumber(7),new LottoNumber(8),new LottoNumber(9),
                new LottoNumber(10),new LottoNumber(11),new LottoNumber(12)));

        LottoTickets manualTickets = new LottoTickets();
        manualTickets.addTicket(manualTicket1);
        manualTickets.addTicket(manualTicket2);

        int totalLottoTicketCount = 5;

        LottoTickets generateTickets = LottoTicketsGenerator.generateTickets(manualTickets,totalLottoTicketCount);

        assertThat(generateTickets.ticketsCount()).isEqualTo(totalLottoTicketCount);
    }
}
