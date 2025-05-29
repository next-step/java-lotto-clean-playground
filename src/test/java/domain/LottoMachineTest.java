package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {
    @Test
    @DisplayName("요청한 개수만큼 로또 티켓을 생성한다")
    void generateTickets_createsCorrectNumberOfTickets() {
        //Given
        LottoMachine lottoMachine = new LottoMachine();
        int count = 5;

        //When
        List<LottoTicket> tickets = lottoMachine.generateTickets(count);

        //Then
        assertThat(tickets).hasSize(count);
    }
}
