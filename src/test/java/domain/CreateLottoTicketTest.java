package domain;


import fakeTest.FakeGenerateRandomNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateLottoTicketTest {


    @Test
    void 로또_가상값_테스트() {

        // given
        FakeGenerateRandomNumber fakeGenerateRandomNumber = new FakeGenerateRandomNumber();
        CreateLottoTicket createLottoTicket = new CreateLottoTicket();

        // when
        createLottoTicket.getLottoNumber();
        List<Integer> expectedValue = fakeGenerateRandomNumber.generateRandomNumber();

        // then
        assertEquals(6, expectedValue.size());
        assertEquals(List.of(11, 23, 32, 44, 5, 16), expectedValue);
    }

    @Test
    void 로또_범위_테스트() {

        // given
        CreateLottoTicket createLottoTicket = new CreateLottoTicket();

        // when
        List<Integer> expectedValue = createLottoTicket.generateRandomNumber();

        // then
        for (int num : expectedValue) {
            assertTrue(1 <= num && num <= 45, "로또 숫자는 1~45까지");
        }
    }
}