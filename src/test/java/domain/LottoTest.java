package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import exception.InvalidLottoNumberCountException;
import exception.LottoNumberDuplicationException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    @DisplayName("로또 생성 테스트")
    void lottoTest() {
        // Given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);

        // When & Then
        assertThat(lotto.getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 생성 예외 테스트: 중복")
    void lottoDuplicationExceptionTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 2, 3, 4, 5))).isInstanceOf(
                LottoNumberDuplicationException.class);
    }

    @Test
    @DisplayName("로또 생성 예외 테스트: 로또 숫자 개수")
    void lottoNumberCountExceptionTest() {
        assertAll(() -> assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5))).isInstanceOf(
                        InvalidLottoNumberCountException.class),
                () -> assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7))).isInstanceOf(
                        InvalidLottoNumberCountException.class));

    }
}
