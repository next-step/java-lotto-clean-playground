package domain;

import enums.LottoType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    @Test
    @DisplayName("로또에 숫자가 정확히 들어가야한다")
    void 로또에_숫자가_정확히_들어가야한다() {
        //given
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto = Lotto.from(numbers, LottoType.AUTO);

        //when
        List<Integer> lottoNumbers = lotto.getLottoNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());

        //then
        assertThat(lottoNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("로또 숫자가 6개가 아니면 예외를 던진다")
    void 로또_숫자가_6개가_아니면_예외를_던진다() {
        //given
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

        //then
        assertThatThrownBy(() -> Lotto.from(numbers, LottoType.AUTO))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또에는_중복된_숫자가_들어갈_수_없다")
    void 로또에는_중복된_숫자가_들어갈_수_없다() {
        //given
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 1, 3, 4, 5, 6));

        //then
        assertThatThrownBy(() -> Lotto.from(numbers, LottoType.AUTO))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
