package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 IllegalArgumentException이 발생한다.")
    void create_Invalid_Lotto_Size() {
        // given
        List<Integer> invalidLottoNumbers = List.of(1, 2, 3, 4, 5); // 5개 숫자

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
        List<Integer> validLottoNumbers = List.of(1, 2, 3, 4, 5, 6); // 6개 숫자

        // when
        Lotto lotto = Lotto.from(validLottoNumbers);

        // then
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

}
