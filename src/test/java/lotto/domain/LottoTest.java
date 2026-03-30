package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat; // 추가
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다.")
    void invalidLottoSize() {
        List<LottoNumber> numbers = createLottoNumbers(Arrays.asList(1, 2, 3, 4, 5));
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호와 몇 개의 번호가 일치하는지 계산한다.")
    void countMatchTest() {
        // given
        Lotto ticket = new Lotto(createLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Lotto winningLotto = new Lotto(createLottoNumbers(Arrays.asList(1, 2, 3, 10, 11, 12)));

        // when
        int matchCount = ticket.countMatch(winningLotto);

        // then
        assertThat(matchCount).isEqualTo(3); // 빨간 줄 해결 포인트
    }

    private List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
