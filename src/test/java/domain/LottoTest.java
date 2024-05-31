package domain;

import fakeTest.FakeGenerateNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoTest {

    @Test
    void 로또_가상값_테스트() {

        // given
        FakeGenerateNumberGenerator fakeGenerateRandomNumber = new FakeGenerateNumberGenerator();
        LottoTickets lottos = new LottoTickets(fakeGenerateRandomNumber);

        // when
        List<LottoTicket> tickets = lottos.generateLottoTickets(1);
        List<Integer> generatedNumbers = tickets.get(0).getNumbers();

        // then
        List<Integer> expectedValue = fakeGenerateRandomNumber.generateRandomNumber();
        Assertions.assertAll(
                () -> assertEquals(expectedValue, generatedNumbers),
                () -> assertEquals(6, generatedNumbers.size()),
                () -> assertEquals(List.of(5, 16, 11, 23, 32, 44), generatedNumbers)
        );
    }

    @Test
    void 로또_범위_테스트() {

        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoTickets lottos = new LottoTickets(numberGenerator);

        // when
        List<LottoTicket> tickets = lottos.generateLottoTickets(1);
        List<Integer> generatedNumbers = tickets.get(0).getNumbers();

        // then
        for (int num : generatedNumbers) {
            assertTrue(1 <= num && num <= 45, "범위 테스트 " + num);
        }
    }
}
