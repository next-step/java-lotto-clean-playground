package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("LottoChecker 클래스")
class LottoCheckerTest {

    private String[] winningNumbers;
    private String bonusNumber;

    @BeforeEach
    void setUp() {
        winningNumbers = new String[]{"1", "2", "3", "4", "5", "6"};
        bonusNumber = "7";
    }

    private LottoTickets createLottoTickets(List<String> numberStrings) {
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addUserSelectedLottos(numberStrings);
        return lottoTickets;
    }

    @Nested
    @DisplayName("생성자 유효성 검사")
    class ConstructorValidation {

        @Test
        @DisplayName("보너스 볼이 숫자가 아닐 경우 예외가 발생한다.")
        void throwExceptionWhenBonusNumberIsNotNumeric() {
            LottoTickets lottoTickets = createLottoTickets(List.of("1, 2, 3, 4, 5, 6"));
            String invalidBonusNumber = "a";

            assertThatThrownBy(() -> new LottoChecker(winningNumbers, lottoTickets, invalidBonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("보너스 볼은 숫자여야 합니다.");
        }

        @Test
        @DisplayName("보너스 볼이 범위를 벗어날 경우 예외가 발생한다.")
        void throwExceptionWhenBonusNumberIsOutOfRange() {
            LottoTickets lottoTickets = createLottoTickets(List.of("1, 2, 3, 4, 5, 6"));
            String invalidBonusNumber = "46";

            assertThatThrownBy(() -> new LottoChecker(winningNumbers, lottoTickets, invalidBonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("보너스 볼은" + Lotto.LOTTO_NUMBER_LOWER_BOUND + "과" + Lotto.LOTTO_NUMBER_BOUND + "사이의 숫자여야 합니다.");
        }
    }

    @Nested
    @DisplayName("단일 티켓 당첨 결과 확인")
    class SingleTicketResult {

        @Test
        @DisplayName("1등 당첨을 확인한다.")
        void checkFirstPrize() {
            LottoTickets lottoTickets = createLottoTickets(List.of("1, 2, 3, 4, 5, 6"));
            LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);

            List<LottoWinningType> results = lottoChecker.checkAllTickets();

            assertThat(results).containsExactly(LottoWinningType.FIRST_PLACE);
        }

        @Test
        @DisplayName("2등 당첨을 확인한다.")
        void checkSecondPrize() {
            LottoTickets lottoTickets = createLottoTickets(List.of("1, 2, 3, 4, 5, 7"));
            LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);

            List<LottoWinningType> results = lottoChecker.checkAllTickets();

            assertThat(results).containsExactly(LottoWinningType.SECOND_PLACE);
        }

        @Test
        @DisplayName("3등 당첨을 확인한다.")
        void checkThirdPrize() {
            LottoTickets lottoTickets = createLottoTickets(List.of("1, 2, 3, 4, 5, 8"));
            LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);

            List<LottoWinningType> results = lottoChecker.checkAllTickets();

            assertThat(results).containsExactly(LottoWinningType.THIRD_PLACE);
        }

        @Test
        @DisplayName("4등 당첨을 확인한다.")
        void checkFourthPrize() {
            LottoTickets lottoTickets = createLottoTickets(List.of("1, 2, 3, 4, 8, 9"));
            LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);

            List<LottoWinningType> results = lottoChecker.checkAllTickets();

            assertThat(results).containsExactly(LottoWinningType.FOURTH_PLACE);
        }

        @Test
        @DisplayName("5등 당첨을 확인한다.")
        void checkFifthPrize() {
            LottoTickets lottoTickets = createLottoTickets(List.of("1, 2, 3, 8, 9, 10"));
            LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);

            List<LottoWinningType> results = lottoChecker.checkAllTickets();

            assertThat(results).containsExactly(LottoWinningType.FIFTH_PLACE);
        }

        @Test
        @DisplayName("꽝을 확인한다.")
        void checkMiss() {
            LottoTickets lottoTickets = createLottoTickets(List.of("1, 2, 8, 9, 10, 11"));
            LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);

            List<LottoWinningType> results = lottoChecker.checkAllTickets();

            assertThat(results).containsExactly(LottoWinningType.NO_PRIZE);
        }
    }

    @Nested
    @DisplayName("여러 티켓 당첨 결과 확인")
    class MultipleTicketsResult {

        @Test
        @DisplayName("여러 티켓의 당첨 결과를 확인한다.")
        void checkMultipleTickets() {
            LottoTickets lottoTickets = createLottoTickets(List.of(
                    "1, 2, 3, 4, 5, 6",
                    "10, 11, 12, 13, 14, 15",
                    "1, 2, 3, 8, 9, 10"
            ));
            LottoChecker lottoChecker = new LottoChecker(winningNumbers, lottoTickets, bonusNumber);

            List<LottoWinningType> results = lottoChecker.checkAllTickets();

            assertThat(results).containsExactly(
                    LottoWinningType.FIRST_PLACE,
                    LottoWinningType.NO_PRIZE,
                    LottoWinningType.FIFTH_PLACE
            );
        }
    }
}
