package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.domain.model.Lotto;
import lotto.domain.model.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 IllegalArgumentException이 발생한다.")
    void create_Invalid_Lotto_Size() {
        // given
        List<LottoNumber> invalidLottoNumbers = createLottoNumbers(1, 2, 3, 4, 5);
        // when & then
        assertThatThrownBy(() -> {
            Lotto.from(invalidLottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 6개일 때 정상 생성된다.")
    void create_Valid_Lotto_Size() {
        // given
        List<LottoNumber> validLottoNumbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        // when
        Lotto lotto = Lotto.from(validLottoNumbers);

        // then
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 번호가 중복되면 IllegalArgumentException이 발생한다.")
    void create_Duplicated_Lotto_Number() {
        // given
        List<LottoNumber> duplicatedLottoNumbers = createLottoNumbers(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> {
            Lotto.from(duplicatedLottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("로또 번호에 특정 번호가 포함되어 있는지 확인한다.")
    void contains_Specific_Number() {
        // given
        List<LottoNumber> numbers = createLottoNumbers(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.from(numbers);

        // when & then
        assertThat(lotto.contains(LottoNumber.valueOf(1))).isTrue();
        assertThat(lotto.contains(LottoNumber.valueOf(6))).isTrue();

        assertThat(lotto.contains(LottoNumber.valueOf(7))).isFalse();
        assertThat(lotto.contains(LottoNumber.valueOf(8))).isFalse();
    }

    private List<LottoNumber> createLottoNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(LottoNumber::valueOf)
            .toList();
    }

}
