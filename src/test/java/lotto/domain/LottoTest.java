package lotto.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoTest {

    @Test
    void 로또_번호가_6개가_아니면_예외가_발생한다() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨_번호와_몇_개의_번호가_일치하는지_계산한다() {
        Lotto ticket = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = Lotto.from(Arrays.asList(1, 2, 3, 10, 11, 12));

        int matchCount = ticket.countMatch(winningLotto);
        assertThat(matchCount).isEqualTo(3);
    }
}
