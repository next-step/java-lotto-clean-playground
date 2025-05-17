package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import exception.InvalidLottoNumberCountException;
import exception.LottoNumberDuplicationException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {
    private static Stream<Arguments> provideLottosForMatchCount() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 40, 41, 42, 43), 2),
                Arguments.of(List.of(1, 2, 3, 40, 41, 42), 3),
                Arguments.of(List.of(1, 2, 3, 4, 40, 41), 4),
                Arguments.of(List.of(1, 2, 3, 4, 5, 40), 5),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6)
        );
    }

    @Test
    @DisplayName("로또 생성 테스트")
    void lottoTest() {
        // Given
        Lotto lotto = new Lotto(new LottoNumbers(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4),
                        LottoNumber.of(5), LottoNumber.of(6))));
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);

        // When & Then
        assertThat(lotto.getNumbers()
                .lottoNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .toList())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 생성 예외 테스트: 중복")
    void lottoDuplicationExceptionTest() {
        assertThatThrownBy(() -> new Lotto(new LottoNumbers(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4),
                        LottoNumber.of(5), LottoNumber.of(1)))))
                .isInstanceOf(LottoNumberDuplicationException.class);
    }

    @Test
    @DisplayName("로또 생성 예외 테스트: 로또 숫자 개수")
    void lottoNumberCountExceptionTest() {
        assertAll(
                () -> assertThatThrownBy(() -> new Lotto(new LottoNumbers(
                        List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4),
                                LottoNumber.of(5)))))
                        .isInstanceOf(InvalidLottoNumberCountException.class),
                () -> assertThatThrownBy(() -> new Lotto(new LottoNumbers(
                        List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4),
                                LottoNumber.of(5), LottoNumber.of(6), LottoNumber.of(7)))))
                        .isInstanceOf(InvalidLottoNumberCountException.class));

    }

    @ParameterizedTest
    @MethodSource("provideLottosForMatchCount")
    @DisplayName("로또 일치 개수 파라미터 테스트")
    void lottoMatchCountParameterizedTest(List<Integer> purchaseNumbers, int expectedMatchCount) {
        // Given
        Lotto winningLotto = new Lotto(new LottoNumbers(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))));

        Lotto purchaseLotto = new Lotto(new LottoNumbers(
                purchaseNumbers.stream().map(LottoNumber::of).toList()));

        // When
        int matchCount = purchaseLotto.getMatchCount(purchaseLotto, winningLotto);

        // Then
        assertThat(matchCount).isEqualTo(expectedMatchCount);
    }
}
