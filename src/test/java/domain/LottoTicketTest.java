package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketTest {
    @DisplayName("로또 숫자 리스트의 길이가 6이고 중복이 없다면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource
    public void testLottoTicket_ValidLottoNumbers(List<LottoNumber> lottoNumbers) {
        // when & then
        assertThatCode(() -> new LottoTicket(lottoNumbers)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> testLottoTicket_ValidLottoNumbers() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                Arguments.arguments(Arrays.asList(LottoNumber.valueOf(11), LottoNumber.valueOf(24), LottoNumber.valueOf(13), LottoNumber.valueOf(42), LottoNumber.valueOf(25), LottoNumber.valueOf(26))),
                Arguments.arguments(Arrays.asList(LottoNumber.valueOf(10), LottoNumber.valueOf(20), LottoNumber.valueOf(30), LottoNumber.valueOf(40), LottoNumber.valueOf(33), LottoNumber.valueOf(16)))
        );
    }

    @DisplayName("로또 숫자 리스트의 길이가 6이 아니면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @MethodSource
    public void testLottoTicket_InvalidLottoNumbersLength(List<LottoNumber> lottoNumbers) {
        // when & then
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 티켓의 숫자는 6개여야 합니다.");
    }

    private static Stream<Arguments> testLottoTicket_InvalidLottoNumbersLength() {
        return Stream.of(
                Arguments.arguments(List.of()),
                Arguments.arguments(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2))),
                Arguments.arguments(Arrays.asList(LottoNumber.valueOf(11), LottoNumber.valueOf(24), LottoNumber.valueOf(13), LottoNumber.valueOf(42), LottoNumber.valueOf(25), LottoNumber.valueOf(26), LottoNumber.valueOf(3)))
        );
    }

    @DisplayName("로또 숫자에 중복이 있으면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @MethodSource
    public void testLottoTicket_DuplicateLottoNumbers(List<LottoNumber> lottoNumbers) {
        // when & then
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 티켓의 숫자는 중복될 수 없습니다.");
    }

    private static Stream<Arguments> testLottoTicket_DuplicateLottoNumbers() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(1), LottoNumber.valueOf(1), LottoNumber.valueOf(1), LottoNumber.valueOf(1), LottoNumber.valueOf(1))),
                Arguments.arguments(Arrays.asList(LottoNumber.valueOf(45), LottoNumber.valueOf(45), LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4)))
        );
    }

    @DisplayName("당첨 번호, 보너스 볼과 티켓 번호를 비교하여 등수를 반환하고, 등수가 없다면 null을 반환한다.")
    @ParameterizedTest
    @MethodSource
    public void testGetLottoRank(LottoTicket lottoticket, LottoTicket winnerTicket, LottoNumber bonusNumber, LottoRank expected) {
        // when
        LottoRank actual = lottoticket.getLottoRank(winnerTicket, bonusNumber);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGetLottoRank() {
        return Stream.of(
                Arguments.arguments(
                        new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                        new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                        LottoNumber.valueOf(7),
                        LottoRank.FIRST
                ),
                Arguments.arguments(
                        new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                        new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(5), LottoNumber.valueOf(6), LottoNumber.valueOf(7))),
                        LottoNumber.valueOf(6),
                        LottoRank.SECOND
                ),
                Arguments.arguments(
                        new LottoTicket(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))),
                        new LottoTicket(Arrays.asList(LottoNumber.valueOf(7), LottoNumber.valueOf(8), LottoNumber.valueOf(9), LottoNumber.valueOf(10), LottoNumber.valueOf(11), LottoNumber.valueOf(12))),
                        LottoNumber.valueOf(41),
                        null
                )
        );
    }
}
