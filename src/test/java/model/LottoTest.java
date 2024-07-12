package model;

import global.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("로또의 숫자가 6개가 아니면 예외를 발생한다.")
    @MethodSource("lottoWithWrongSize")
    @ParameterizedTest
    void lotto_size_6(List<Integer> numbers) {
        // given
        // when
        // then
        assertThatThrownBy(() -> Lotto.fromNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 6개의 숫자로 구성되어야 합니다.");
    }

    private static Stream<Arguments> lottoWithWrongSize() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)));
    }

    @DisplayName("로또 내 숫자는 중복되면 예외를 발생한다.")
    @MethodSource("lottoWithDuplicatedNumber")
    @ParameterizedTest
    void lotto_with_duplicated_number(List<Integer> numbers) {
        // given
        // when
        // then
        assertThatThrownBy(() -> Lotto.fromNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 내 동일한 숫자가 있으면 안됩니다.");
    }

    private static Stream<Arguments> lottoWithDuplicatedNumber() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 5)),
                Arguments.of(List.of(1, 1, 3, 4, 5, 6)));
    }

    @DisplayName("로또 내 숫자가 1과 45사이의 숫자가 아니면 예외를 발생한다.")
    @MethodSource("lottoWithWrongNumber")
    @ParameterizedTest
    void lotto_with_not_1_to_45(List<Integer> numbers) {
        // given
        // when
        // then
        assertThatThrownBy(() -> Lotto.fromNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호 및 보너스 볼은 1과 45사이의 숫자이어야 합니다.");
    }

    private static Stream<Arguments> lottoWithWrongNumber() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 46)),
                Arguments.of(List.of(0, 1, 3, 4, 5, 6)));
    }

    @DisplayName("우승 로또가 주어졌을 때 일치 개수에 따라 등수를 반환한다.")
    @MethodSource("winningLottoAndRank")
    @ParameterizedTest(name = "우승 로또가 {0}이고 보너스 볼이 {1}일 때 등수는 {2}이다.")
    void get_rank_with_winning_lotto(List<Integer> winningNumber, Integer number, Rank expect) {
        // given
        final Lotto lotto = Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        final Lotto winningLotto = Lotto.fromNumbers(winningNumber);
        final LottoNumber bonusNumber = new LottoNumber(number);

        // when
        final Rank result = lotto.getRank(winningLotto, bonusNumber);

        // then
        assertThat(result).isEqualTo(expect);
    }

    private static Stream<Arguments> winningLottoAndRank() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 7, 8, 9, 10, 11), 6, Rank.LAST_PLACE),
                Arguments.of(Arrays.asList(1, 2, 3, 9, 10, 11), 6, Rank.FIFTH_PLACE),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 10, 11), 6, Rank.FOURTH_PLACE),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 11), 10, Rank.THIRD_PLACE),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 10), 6, Rank.SECOND_PLACE),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 11, Rank.FIRST_PLACE));
    }

    @DisplayName("로또 입력이 숫자로만 구성되어 있지 않으면 예외를 발생한다.")
    @MethodSource("wrongLottoInput")
    @ParameterizedTest
    void lotto_without_number(String[] input) {
        // given
        // when
        // then
        assertThatThrownBy(() -> Lotto.fromStringsInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자입력만 허용합니다.");
    }

    private static Stream<Arguments> wrongLottoInput() {
        return Stream.of(
                Arguments.of((Object) new String[]{"1ab", "1", "2", "4"}),
                Arguments.of((Object) new String[]{"일", "이", "삼"}),
                Arguments.of((Object) new String[]{"!@#", "$%^", "&*"}));
    }
}
