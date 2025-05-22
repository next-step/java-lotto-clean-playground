package domain;

import exception.lotto.InvalidLottoLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.LottoFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LottoTest {

    @DisplayName("발급 받은 로또가 6개가 아니면 => throws InvalidLottoLengthException")
    @ParameterizedTest(name = "발급 받은 로또가 {0}이면 사이즈를 벗어난다.")
    @MethodSource("invalidLengthLottos")
    void whenLottoSizeOutOfBound_thenThrowRuntimeException(List<Integer> invalidLottoNumbers) {
        assertThatThrownBy(() -> LottoFactory.generateManualLotto(invalidLottoNumbers))
                .isInstanceOf(InvalidLottoLengthException.class);
    }

    private static Stream<Arguments> invalidLengthLottos() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4, 5)),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.arguments(List.of())
        );
    }

    @ParameterizedTest
    @DisplayName("발급 받은 로또의 번호에 따라 맞춘 번호의 갯수와 bonus 볼 성공 여부를 적절히 구한다.")
    @MethodSource("lottoNumbersAndExpectedMatchCountWithWinningNumbers")
    void whenFixedNumberIncludedLottoGenerated_thenCountMatchedNumberExactly(WinningNumbers winningNumbers, Lotto lotto, int expectedMatchCount, boolean expectedIsIncludeBonusNumber) {
        assertAll(
                () -> assertThat(lotto.countMatchedNumbers(winningNumbers)).isEqualTo(expectedMatchCount),
                () -> assertThat(lotto.isContainBonusBallNumber(winningNumbers)).isEqualTo(expectedIsIncludeBonusNumber)
        );

    }

    private static Stream<Arguments> lottoNumbersAndExpectedMatchCountWithWinningNumbers() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 4);
        return Stream.of(
                Arguments.arguments(winningNumbers, LottoFactory.generateManualLotto(List.of(1, 2, 3, 4, 5, 6)), 6, true),
                Arguments.arguments(winningNumbers, LottoFactory.generateManualLotto(List.of(2, 3, 4, 5, 6, 7)), 5, true),
                Arguments.arguments(winningNumbers, LottoFactory.generateManualLotto(List.of(3, 4, 5, 6, 7, 8)), 4, true),
                Arguments.arguments(winningNumbers, LottoFactory.generateManualLotto(List.of(4, 5, 6, 7, 8, 9)), 3, true),
                Arguments.arguments(winningNumbers, LottoFactory.generateManualLotto(List.of(5, 6, 7, 8, 9, 10)), 2, false),
                Arguments.arguments(winningNumbers, LottoFactory.generateManualLotto(List.of(6, 7, 8, 9, 10, 11)), 1, false),
                Arguments.arguments(winningNumbers, LottoFactory.generateManualLotto(List.of(7, 8, 9, 10, 11, 12)), 0, false),
                Arguments.arguments(winningNumbers, LottoFactory.generateManualLotto(List.of(8, 9, 10, 11, 12, 13)), 0, false)
        );
    }

    @Test
    @DisplayName("로또에 중복된 숫자가 존재하면 예외를 발생시킨다.")
    void whenDuplicatedNumbersAreInclude_thenThrowIllegalArgumentException() {
        assertThatThrownBy(() -> LottoFactory.generateManualLotto(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또에 중복된 번호가 포함되어 있습니다.");
    }
}
