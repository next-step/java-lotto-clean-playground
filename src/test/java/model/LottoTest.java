package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @Test
    @DisplayName("로또번호가 6개보다 작거나 큰 경우 예외를 발생한다.")
    void invalidSizeThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5))
        );

        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @Test
    @DisplayName("로또번호는 중복될 경우 예외를 발생한다.")
    void duplicateNumbersThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 5))
        );
    }

    @Test
    @DisplayName("로또번호는 1~45 사이의 숫자가 아닌 경우 예외를 발생한다.")
    void outOfRangeThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(0, 1, 2, 3, 4, 5))
        );

        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 46))
        );
    }

    @Test
    @DisplayName("로또번호는 자동으로 생성할 수 있다.")
    void createAutoLotto() {
        Lotto lotto = new Lotto();

        assertNotNull(lotto, "자동 생성된 로또 객체가 null입니다.");
    }

    @Test
    @DisplayName("로또번호는 수동으로 생성할 수 있다.")
    void createManualLotto() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Lotto lotto = new Lotto(numbers);

        assertNotNull(lotto, "수동 생성된 로또 객체가 null입니다.");
    }

    @Test
    @DisplayName("수동으로 생성된 로또 번호는 정렬되어야 한다.")
    void manualLottoShouldBeSorted() {
        List<Integer> manualNumbers = Arrays.asList(6, 3, 1, 5, 4, 2);

        Lotto manualLotto = new Lotto(manualNumbers);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), manualLotto.getSortedNumbers(), "수동 로또 번호가 정렬되지 않았습니다.");
    }

    @Test
    @DisplayName("자동으로 생성된 로또 번호는 정렬되어야 한다.")
    void autoLottoShouldBeSorted() {
        Lotto autoLotto = new Lotto();
        List<Integer> autoNumbers = autoLotto.getSortedNumbers();

        assertEquals(autoNumbers, autoNumbers.stream().sorted().toList(), "자동 생성된 로또 번호가 정렬되지 않았습니다.");
    }
}
