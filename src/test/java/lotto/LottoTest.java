package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("같은 번호 목록을 가진 Lotto는 같은 값")
    void voCheck() {
        List<LottoNumbers> numbers1 = List.of(
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6))
        );
        List<LottoNumbers> numbers2 = List.of(
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6))
        );

        Lotto first = new Lotto(numbers1);
        Lotto second = new Lotto(numbers2);

        assertThat(first.getNumbers()).isEqualTo(second.getNumbers());
    }

    @Test
    @DisplayName("리스트 수정을 했을 때 예외 발생(불변 객체 확인)")
    void shouldBeImmutable() {
        List<LottoNumbers> originalNumbers = List.of(
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6))
        );
        Lotto lotto = new Lotto(originalNumbers);

        List<LottoNumbers> numbers = lotto.getNumbers();
        assertThat(numbers).isEqualTo(originalNumbers);

        assertThrows(UnsupportedOperationException.class, () ->
            numbers.add(new LottoNumbers(Arrays.asList(7, 8, 9, 10, 11, 12)))
        );
    }
}
