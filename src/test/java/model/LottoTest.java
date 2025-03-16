package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @Test
    @DisplayName("로또 번호가 6개보다 작거나 많으면 예외가 발생한다.")
    void invalidSizeThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5)) // 5개 → 예외 발생
        );

        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7)) // 7개 → 예외 발생
        );
    }

    @Test
    @DisplayName("로또 번호가 중복되면 예외가 발생한다.")
    void duplicateNumbersThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 5)) // 중복된 숫자 → 예외 발생
        );
    }

    @Test
    @DisplayName("로또 번호가 1~45 사이가 아니면 예외 발생")
    void outOfRangeThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(0, 1, 2, 3, 4, 5)) // 0 포함 → 예외 발생
        );

        assertThrows(IllegalArgumentException.class, () ->
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 46)) // 46 포함 → 예외 발생
        );
    }

    @Test
    @DisplayName("자동으로 로또 번호를 생성할 수 있다.")
    void createAutoLottoNumbers() {
        LottoNumbers autoLottoNumbers = new LottoNumbers(); // 자동 생성
        assertNotNull(autoLottoNumbers, "자동 생성된 로또 번호가 null입니다.");
        assertEquals(6, autoLottoNumbers.getNumbers().size(), "자동 생성된 로또 번호 개수가 6개가 아닙니다.");
    }

    @Test
    @DisplayName("수동으로 로또 번호를 생성할 수 있다.")
    void createManualLottoNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoNumbers manualLottoNumbers = new LottoNumbers(numbers); // 수동 생성
        assertNotNull(manualLottoNumbers, "수동 생성된 로또 번호가 null입니다.");
        assertEquals(numbers, manualLottoNumbers.getNumbers(), "수동 생성된 로또 번호가 입력과 다릅니다.");
    }

    @Test
    @DisplayName("수동으로 생성된 로또 번호는 정렬되어야 한다.")
    void manualLottoNumbersShouldBeSorted() {
        List<Integer> manualNumbers = Arrays.asList(6, 3, 1, 5, 4, 2);
        LottoNumbers manualLottoNumbers = new LottoNumbers(manualNumbers);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), manualLottoNumbers.getNumbers(), "수동 로또 번호가 정렬되지 않았습니다.");
    }

    @Test
    @DisplayName("자동으로 생성된 로또 번호는 정렬되어야 한다.")
    void autoLottoNumbersShouldBeSorted() {
        LottoNumbers autoLottoNumbers = new LottoNumbers();
        List<Integer> autoNumbers = autoLottoNumbers.getNumbers();

        assertEquals(autoNumbers, autoNumbers.stream().sorted().toList(), "자동 생성된 로또 번호가 정렬되지 않았습니다.");
    }

    @Test
    @DisplayName("로또 객체는 LottoNumbers를 포함해야 한다.")
    void lottoShouldContainLottoNumbers() {
        LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(lottoNumbers);

        assertEquals(lottoNumbers.getNumbers(), lotto.getNumbers(), "Lotto 객체의 번호가 LottoNumbers와 다릅니다.");
    }

    @Test
    @DisplayName("로또 티켓을 수동으로 여러 장 생성할 수 있다.")
    void createManualLottoTickets() {
        List<List<Integer>> manualNumbers = List.of(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(10, 11, 12, 13, 14, 15)
        );
        LottoTickets manualTickets = new LottoTickets(manualNumbers); // 수동 티켓 생성

        assertEquals(2, manualTickets.getTickets().size(), "수동 로또 티켓 개수가 맞지 않습니다.");
    }

    @Test
    @DisplayName("로또 티켓을 자동으로 여러 장 생성할 수 있다.")
    void createAutoLottoTickets() {
        LottoTickets autoTickets = new LottoTickets(3); // 자동 티켓 3장 생성

        assertEquals(3, autoTickets.getTickets().size(), "자동 로또 티켓 개수가 맞지 않습니다.");
    }

    @Test
    @DisplayName("수동과 자동 티켓을 합칠 수 있다.")
    void mergeManualAndAutoLottoTickets() {
        List<List<Integer>> manualNumbers = List.of(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(10, 11, 12, 13, 14, 15)
        );
        LottoTickets manualTickets = new LottoTickets(manualNumbers);
        LottoTickets autoTickets = new LottoTickets(2);

        LottoTickets mergedTickets = LottoTickets.merge(manualTickets, autoTickets);
        assertEquals(4, mergedTickets.getTickets().size(), "수동 + 자동 로또 티켓 개수가 맞지 않습니다.");
    }
}
