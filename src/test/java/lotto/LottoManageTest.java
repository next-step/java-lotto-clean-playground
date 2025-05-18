package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class LottoManageTest {

    @Test
    @DisplayName("숫자 확인")
    void pullOutNumbersTest() {
        List<Integer> numbers = LottoManage.pullOutNumbers();

        assertEquals(45, numbers.size());
        assertEquals(1, numbers.get(0));
        assertEquals(45, numbers.get(44));
    }

    @Test
    @DisplayName("숫자 섞기")
    void shuffleNumbersTest() {
        List<Integer> firstNumbers = LottoManage.pullOutNumbers();
        List<Integer> endNumbers = new ArrayList<>(firstNumbers);

        LottoManage.shuffleNumbers(endNumbers);

        assertNotEquals(firstNumbers, endNumbers);
//        assertEquals(firstNumbers, endNumbers);
        // 해당 결과가 같지 않으면 테스트 통과
    }

    @Test
    @DisplayName("숫자 인덱스 정렬")
    void pickupLottoNumbersTest() {
        List<Integer> numbers = LottoManage.pullOutNumbers();
        LottoManage.shuffleNumbers(numbers);
        List<Integer> lottoNumbers = LottoManage.pickupLottoNumbers(numbers);

        assertEquals(6, lottoNumbers.size());
        // 로또 번호는 한 줄당 6개
    }
}