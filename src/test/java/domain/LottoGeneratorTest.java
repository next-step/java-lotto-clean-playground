package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {
    @Test
    @DisplayName("자동 생성된 로또 번호는 중복되지 않는다")
    void lottoNumbersShouldNotContainDuplicates() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        Lotto lotto = lottoGenerator.generate();

        assertThat(lotto.getNumbers()).doesNotHaveDuplicates();
    }

    @Test
    @DisplayName("자동 생성된 로또는 6개의 번호를 가진다")
    void lottoShouldContainsSixNumbers() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        Lotto lotto = lottoGenerator.generate();

        assertThat(lotto.getNumbers()).hasSize(6);
    }
}
