package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;

class WinningLottoTest {

    @Test
    @DisplayName("1부터 6까지의 유효한 당첨 번호로 객체를 생성할 수 있다")
    void createWinningLottoWithValidNumbers() {
        List<LottoNumber> numbers = convert(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(numbers);

        assertThat(winningLotto.getNumbers()).containsExactlyElementsOf(numbers);
    }

    @Test
    @DisplayName("당첨 번호와 일치하는 번호의 개수를 정확히 계산할 수 있다")
    void countMatchReturnsCorrectCount() {
        List<LottoNumber> winningNumbers = convert(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers);

        List<LottoNumber> userNumbers = convert(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto = new Lotto(userNumbers);

        int matchCount = winningLotto.countMatch(lotto);

        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    @DisplayName("당첨 번호 개수가 6개가 아니면 예외가 발생한다")
    void invalidSizeThrowsException() {
        List<LottoNumber> invalidNumbers = convert(List.of(1, 2, 3));

        assertThatThrownBy(() -> new WinningLotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호에 중복이 있으면 예외가 발생한다")
    void duplicateNumbersThrowsException() {
        List<LottoNumber> duplicated = convert(List.of(1, 2, 3, 4, 5, 5));

        assertThatThrownBy(() -> new WinningLotto(duplicated))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 중복될 수 없습니다.");
    }

    private List<LottoNumber> convert(List<Integer> raw) {
        return raw.stream().map(LottoNumber::new).collect(toList());
    }
}
