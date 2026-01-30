
import domain.LottoGenerator;
import domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoGenerator 테스트")
class LottoGeneratorTest {

    private final LottoGenerator generator = new LottoGenerator();

    @Nested
    @DisplayName("generate")
    class Generate {

        @Test
        @DisplayName("요청한 개수만큼 티켓을 생성한다")
        void generatesAsManyAsRequested() {
            List<LottoTicket> tickets = generator.generate(5);

            assertThat(tickets).hasSize(5);
        }

        @Test
        @DisplayName("각 티켓은 6개 번호이며 중복이 없고 1~45 범위이며 오름차순 정렬이다")
        void generatedTicketRules() {
            List<LottoTicket> tickets = generator.generate(30);

            tickets.forEach(ticket -> {
                List<Integer> numbers = ticket.numbers();

                assertThat(numbers).hasSize(6);
                assertThat(numbers).doesNotHaveDuplicates();
                assertThat(numbers).allMatch(n -> n >= 1 && n <= 45);
                assertThat(numbers).isSorted();
            });
        }
    }
}
