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

    @Test
    @DisplayName("생성된 로또 티켓은 6개의 숫자를 가진다")
    void eachTicket_has_SixNumbers() {
        //Given
        LottoMachine lottoMachine = new LottoMachine();
        List<LottoTicket> lottoTickets = lottoMachine.generateTickets(10);

        for (LottoTicket lottoTicket : lottoTickets) {
            List<Integer> numbers = lottoTicket.getNumbers();
            assertThat(numbers).hasSize(6);
        }
    }
}
