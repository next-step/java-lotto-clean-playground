package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    @DisplayName("로또 번호는 중복되지 않는다")
    void lottoNumbersShouldNotContainsDuplicates() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers()).doesNotHaveDuplicates();
    }

    @Test
    @DisplayName("로또 번호는 1부터 45 사이이다")
    void lottoNumbersShouldBeBetweenOneAndFortyFive() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers())
                .allMatch(lottoNumber -> lottoNumber.getNumber() >= 1
                        && lottoNumber.getNumber() <= 45);
    }

    @Test
    @DisplayName("로또는 6개의 번호를 가진다")
    void lottoShouldContainsSizNumbers() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers()).hasSize(6);
    }
}
