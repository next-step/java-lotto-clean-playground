package domain;

import fakeTest.FakeGenerateRandomNumber;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateRandomNumberTest {

    @Test
    void 로또_난수_범위_테스트() {

        // given
        GenerateRandomNumber lottoRandom = new GenerateRandomNumber();
        List<Integer> lottoNum = lottoRandom.generateNumber();

        // when&then
        for (int num : lottoNum) {
            assertTrue(1 <= num && num <= 45, "범위오버");
        }
    }

    @Test
    void 무작위_숫자_테스트() {

        // given
        FakeGenerateRandomNumber fakeGenerateRandomNumber = new FakeGenerateRandomNumber();

        // when
        List<Integer> lottoNum = fakeGenerateRandomNumber.generateNumber();

        // then
        assertEquals(6, lottoNum.size());
        assertEquals(List.of(11, 23, 32, 44, 5, 16), lottoNum);
    }

}