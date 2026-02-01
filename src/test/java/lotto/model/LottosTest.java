package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.domain.model.Lotto;
import lotto.domain.model.LottoNumber;
import lotto.domain.model.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("로또 목록의 개수를 정확히 반환한다")
    void size_ShouldReturnCorrectCount() {
        // given
        Lotto lotto1 = Lotto.from(createLottoNumbers(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(createLottoNumbers(7, 8, 9, 10, 11, 12));
        Lottos lottos = new Lottos(List.of(lotto1, lotto2));

        // when & then
        assertThat(lottos.getValues()).hasSize(2);
    }

    @Test
    @DisplayName("반환된 로또 목록은 외부에서 수정할 수 없다")
    void getValues_ShouldReturnUnmodifiableList() {
        // given
        Lotto lotto = Lotto.from(createLottoNumbers(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(lotto));
        List<Lotto> values = lottos.getValues();

        // when & then
        assertThatThrownBy(() -> values.add(lotto))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    private List<LottoNumber> createLottoNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(LottoNumber::valueOf)
            .toList();
    }

}
