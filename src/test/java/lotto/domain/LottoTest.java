package lotto.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoTest {

    @Test
    void 로또_번호가_6개가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(Arrays.asList(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨_번호와_일치하는_개수를_계산한다() {
        Lotto lotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto target = Lotto.from(Arrays.asList(1, 2, 3, 7, 8, 9));

        assertThat(lotto.countMatch(target)).isEqualTo(3);
    }

    @Test
    void 보너스_번호를_포함하고_있는지_확인한다() {
        Lotto lotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = LottoNumber.valueOf(6);

        assertThat(lotto.contains(bonus)).isTrue();
    }
}
