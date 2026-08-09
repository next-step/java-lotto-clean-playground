import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ExceptionTest {
    @Test
    @DisplayName("객체 생성 시 숫자가 6개가 아니거나 로또 번호를 초과 혹은 미만이면 예외를 던진다.")
    void validateLottoNumberTest() {
        //given
        List<Integer> testLottoNormal = List.of(1, 2, 3, 4, 5, 6); // 정상 로또
        List<Integer> testLottohasException = List.of(1, 2, 3, 4, 5); // 숫자가 5개인 로또
        List<Integer> testLottohasException2 = List.of(1, 2, 3, 4, 5, 46); // 로또 숫자를 초과하는 로또
        List<Integer> testLottohasException3 = List.of(0, 1, 2, 3, 4, 5); // 로또 숫자 미만인 로또

        //then
        assertAll(
                () -> assertThatCode(() -> new Lotto(testLottoNormal)).doesNotThrowAnyException(),
                () -> assertThatCode(() -> new Lotto(testLottohasException)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 번호는 6개여야 합니다."),
                () -> assertThatCode(() -> new Lotto(testLottohasException2)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 번호는 1부터 45까지여야 합니다."),
                () -> assertThatCode(() -> new Lotto(testLottohasException3)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 번호는 1부터 45까지여야 합니다.")
        );
    }

    @Test
    @DisplayName("객체 생성 시 숫자가 중복되면 예외를 던진다.")

    void validateDuplicatonTest() {
        List<Integer> duplicateLotto = List.of(1, 1, 2, 3, 4, 5);

        assertThatCode(() -> new Lotto(duplicateLotto)).isInstanceOf(IllegalArgumentException.class).hasMessage("중복된 로또 번호가 존재합니다.");
    }
}
