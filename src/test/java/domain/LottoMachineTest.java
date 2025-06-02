package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    @DisplayName("수동 티켓과 자동 티켓을 합쳐 원하는 수만큼 티켓을 생성한다")
    void generate_mixed_lotto_tickets() {
        // Given
        LottoMachine machine = new LottoMachine();
        List<Lotto> handTickets = List.of(
            Lotto.from("1,2,3,4,5,6"),
            Lotto.from("7,8,9,10,11,12")
        );
        int autoCount = 3;

        // When
        List<Lotto> result = machine.generateTickets(handTickets, autoCount);

        // Then
        assertThat(result).hasSize(5); // 2 수동 + 3 자동
    }

    @Test
    @DisplayName("자동 생성된 번호는 오름차순으로 정렬되어 있다")
    void generate_lotto_ticket_should_be_sorted() {
        // Given
        LottoMachine machine = new LottoMachine();

        // When
        List<Integer> numbers = machine.generateTickets(List.of(), 1).get(0).getNumbers();

        // Then
        assertThat(numbers).isSorted();
    }
}
