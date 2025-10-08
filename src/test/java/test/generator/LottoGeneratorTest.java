package test.generator;

import domain.Lotto;
import generator.AutoLottoTicketsGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoGeneratorTest {

    private static boolean isSorted(List<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i - 1) > numbers.get(i)) return false;
        }
        return true;
    }

    @Test
    void 한장_생성하면_숫자_6개_범위_중복없고_정렬되어_있다() {
        // given
        AutoLottoTicketsGenerator generator = new AutoLottoTicketsGenerator();

        // when
        Lotto lotto = new Lotto(generator.generateSixLottoNumber());
        List<Integer> numbers = lotto.getNumbers();

        // then
        assertEquals(6, numbers.size());
        assertEquals(6, new HashSet<>(numbers).size());
        for (int number : numbers) assertTrue(1 <= number && number <= 45);
        assertTrue(isSorted(numbers));
    }

    @Test
    void 여러장_생성하면_요청한_개수만큼_반환한다() {
        // given
        AutoLottoTicketsGenerator generator = new AutoLottoTicketsGenerator();
        int ticketCount = 14;

        // when
        List<Lotto> tickets = generator.generateAutoLottoTickets(ticketCount);

        // then
        assertEquals(ticketCount, tickets.size());
        for (Lotto ticket : tickets) {
            List<Integer> numbers = ticket.getNumbers();
            assertEquals(6, numbers.size());
            assertEquals(6, new HashSet<>(numbers).size());
            for (int number : numbers) assertTrue(1 <= number && number <= 45);
            assertTrue(isSorted(numbers));
        }
    }

    @Test
    void 개수가_0이면_빈_리스트를_반환한다() {
        // given
        AutoLottoTicketsGenerator generator = new AutoLottoTicketsGenerator();

        // when
        List<Lotto> tickets = generator.generateAutoLottoTickets(0);

        // then
        assertTrue(tickets.isEmpty());
    }
}
