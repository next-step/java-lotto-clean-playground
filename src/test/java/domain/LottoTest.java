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
                .allMatch(number -> number >= 1 && number <= 45);
    }
}
