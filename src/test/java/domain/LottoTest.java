package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    void validateNumbers() {
        // 로또 숫자가 6개인 경우
        List<LottoNumber> validNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );
        assertThat(new Lotto(validNumbers)).isNotNull();

        // 로또 숫자가 6개가 아닌 경우
        List<LottoNumber> invalidNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5)
        );
        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자는 6개여야 합니다.");

        // 로또 숫자에 중복이 있는 경우
        List<LottoNumber> duplicatedNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(5)
        );
        assertThatThrownBy(() -> new Lotto(duplicatedNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자는 중복 되지 않아야 합니다.");
    }

    @Test
    void generate() {
        Lotto lotto = Lotto.generate();
        assertThat(lotto.numbers().size()).isEqualTo(6);
        assertThat(lotto.numbers().stream().distinct().count()).isEqualTo(6);
    }

    @Test
    void contains() {
        Lotto lotto = new Lotto(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        assertThat(lotto.contains(new LottoNumber(1))).isTrue();
        assertThat(lotto.contains(new LottoNumber(7))).isFalse();
    }
}
