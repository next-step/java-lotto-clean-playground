package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외 발생")
    void lottoNumberCountInvalid() {
        List<LottoNumber> fiveNumbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> new Lotto(fiveNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호와 일치하는 개수를 반환한다")
    void countMatching_returnsCorrectCount() {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        List<LottoNumber> winningNumbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(7),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)
        );

        int matchingCount = lotto.countMatching(winningNumbers);
        assertThat(matchingCount).isEqualTo(2);
    }

    @Test
    @DisplayName("로또 번호가 포함되어 있는 경우 true를 반환한다")
    void contains_returnsTrueWhenPresent() {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        assertThat(lotto.contains(new LottoNumber(3))).isTrue();
    }

    @Test
    @DisplayName("로또 번호가 포함되어 있지 않은 경우 false를 반환한다")
    void contains_returnsFalseWhenAbsent() {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        assertThat(lotto.contains(new LottoNumber(10))).isFalse();
    }
}
