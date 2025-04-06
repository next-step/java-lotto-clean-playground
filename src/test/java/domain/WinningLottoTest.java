package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class WinningLottoTest {

    static Stream<Arguments> provideWinningLottos() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7),
                Arguments.of(Arrays.asList(10, 11, 12, 13, 14, 15), 16)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWinningLottos")
    @DisplayName("당첨 번호와 보너스 번호가 정상적으로 저장되어야 한다.")
    void create_WinningLotto_Success_Test(List<Integer> lottoNumbers, int bonusNumber) {
        Lotto lotto = new Lotto(lottoNumbers.stream().map(LottoNumber::new).toList());
        LottoNumber bonus = new LottoNumber(bonusNumber);

        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        assertThat(winningLotto.getWinningNumbers()).containsExactlyElementsOf(lotto.numbers());
        assertThat(winningLotto.getBonusNumber()).isEqualTo(bonus);
    }

    static Stream<Arguments> provideDuplicateBonusNumbers() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(Arrays.asList(10, 11, 12, 13, 14, 15), 12)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDuplicateBonusNumbers")
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void Duplicate_BonusNumber_ExceptionThrown(List<Integer> lottoNumbers, int duplicateBonus) {
        Lotto lotto = new Lotto(lottoNumbers.stream().map(LottoNumber::new).toList());
        LottoNumber bonus = new LottoNumber(duplicateBonus);

        assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    static Stream<Arguments> provideUnsortedLottos() {
        return Stream.of(
                Arguments.of(Arrays.asList(5, 3, 1, 6, 2, 4), Arrays.asList(1, 2, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(10, 8, 6, 12, 9, 7), Arrays.asList(6, 7, 8, 9, 10, 12))
        );
    }

    @ParameterizedTest
    @MethodSource("provideUnsortedLottos")
    @DisplayName("당첨 번호는 오름차순 정렬되어 반환되어야 한다.")
    void WinningNumbers_Sort_Test(List<Integer> unsortedNumbers, List<Integer> expectedSortedNumbers) {
        Lotto lotto = new Lotto(unsortedNumbers.stream().map(LottoNumber::new).toList());
        LottoNumber bonus = new LottoNumber(20); //아무 보너스 번호

        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        List<LottoNumber> expectedLottoNumbers = expectedSortedNumbers.stream().map(LottoNumber::new).toList();
        assertThat(winningLotto.getWinningNumbers()).isEqualTo(expectedLottoNumbers);
    }
}
