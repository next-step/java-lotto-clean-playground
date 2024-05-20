package domain;

import fakeTest.FakeGenerateNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoTicketStoreTest {
    @Test
    void 로또_가상값_테스트() {

        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        FakeGenerateNumberGenerator fakeGenerateRandomNumber = new FakeGenerateNumberGenerator();
        LottoTicketStore lottoTicketStore = new LottoTicketStore(numberGenerator);

        // when
        lottoTicketStore.getLottoNumber();
        List<Integer> expectedValue = fakeGenerateRandomNumber.generateRandomNumber();

        // then
        assertEquals(6, expectedValue.size());
        assertEquals(List.of(5, 16, 11, 23, 32, 44), expectedValue);
    }

    @Test
    void 로또_범위_테스트() {

        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoTicketStore lottoTicketStore = new LottoTicketStore(numberGenerator);

        // when
        List<Integer> expectedValue = lottoTicketStore.getLottoNumber();

        // then
        for (int num : expectedValue) {
            assertTrue(1 <= num && num <= 45, "로또 숫자는 1~45까지");
        }
    }
}