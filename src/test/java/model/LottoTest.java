package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    @DisplayName("로또 번호 리스트가 6개이면 정상적으로 만들어지는 지 검증한다.")
    void should_CreateLotto_When_ValidNumbers() {

        List<Integer> validLottoNumbers = List.of(1, 2, 3, 4, 5, 6);

        Lotto lotto = new Lotto(validLottoNumbers);

        assertThat(lotto.getLottoNumbers())
                .hasSize(6)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("로또 번호 리스트가 6개 미만이면 예외가 발생하는 지 검증한다.")
    void should_ThrowException_When_FewerThanSixNumbers() {

        List<Integer> invalidLottoNumbers = List.of(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> new Lotto(invalidLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호 리스트에 중복이 있으면 예외가 발생하는 지 검증한다.")
    void should_ThrowException_When_DuplicatesFound() {

        List<Integer> lottoNumbersWithDuplicates = List.of(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> new Lotto(lottoNumbersWithDuplicates))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호에 중복이 있습니다.");
    }

    @Test
    @DisplayName("로또 번호 리스트를 정렬하여 String으로 출력하는 지 검증한다.")
    void should_ReturnSortedString_When_ToString() {

        List<Integer> validLottoNumbers = List.of(3, 1, 6, 5, 4, 2);
        Lotto lotto = new Lotto(validLottoNumbers);

        String lottoTickets = lotto.toStringLottoTickets();

        assertThat(lottoTickets)
                .isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}
