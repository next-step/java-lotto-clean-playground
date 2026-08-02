package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class LottoTest {
    @Test
    @DisplayName("로또 번호는 중복되지 않는다")
    void lottoNumbersShouldNotContainsDuplicates() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers()).doesNotHaveDuplicates();
    }

    @Test
    @DisplayName("로또 번호는 1부터 45 사이이다")
    void lottoNumbersShouldBeBetweenOneAndFortyFive() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers())
                .allMatch(lottoNumber -> lottoNumber.getNumber() >= 1
                        && lottoNumber.getNumber() <= 45);
    }

    @Test
    @DisplayName("로또는 6개의 번호를 가진다")
    void lottoShouldContainsSizNumbers() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @ParameterizedTest
    @MethodSource("invalidLottoNumbers")
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    void throwsExceptionWhenLottoNumberCountIsNotSix(List<Integer> numbers) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(numbers)
        );
    }

    static Stream<List<Integer>> invalidLottoNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7)
        );
    }
}
