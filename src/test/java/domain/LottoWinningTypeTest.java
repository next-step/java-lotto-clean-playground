package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoWinningType Enum")
class LottoWinningTypeTest {

    @Nested
    @DisplayName("of 메소드는")
    class OfTest {

        @DisplayName("일치 개수와 보너스 여부에 따라 정확한 등수를 반환한다.")
        @ParameterizedTest
        @CsvSource({
                "6, false, FIRST_PLACE",
                "5, true, SECOND_PLACE",
                "5, false, THIRD_PLACE",
                "4, false, FOURTH_PLACE",
                "3, false, FIFTH_PLACE",
                "2, false, NO_PRIZE",
                "1, false, NO_PRIZE",
                "0, false, NO_PRIZE"
        })
        void returnsCorrectWinningType(int matchCount, boolean matchBonus, LottoWinningType expectedType) {
            LottoWinningType actualType = LottoWinningType.of(matchCount, matchBonus);

            assertThat(actualType).isEqualTo(expectedType);
        }
    }

    @Nested
    @DisplayName("calculatePrize 메소드는")
    class CalculatePrizeTest {

        @Test
        @DisplayName("각 등수별 정확한 상금을 계산한다.")
        void calculatesCorrectPrize() {
            assertThat(LottoWinningType.FIRST_PLACE.calculatePrize(1)).isEqualTo(2_000_000_000);
            assertThat(LottoWinningType.SECOND_PLACE.calculatePrize(1)).isEqualTo(30_000_000);
            assertThat(LottoWinningType.THIRD_PLACE.calculatePrize(1)).isEqualTo(1_500_000);
            assertThat(LottoWinningType.FOURTH_PLACE.calculatePrize(1)).isEqualTo(50_000);
            assertThat(LottoWinningType.FIFTH_PLACE.calculatePrize(1)).isEqualTo(5_000);
            assertThat(LottoWinningType.NO_PRIZE.calculatePrize(1)).isEqualTo(0);
        }

        @Test
        @DisplayName("여러 티켓 당첨 시 총 상금을 계산한다.")
        void calculatesCorrectTotalPrizeForMultipleTickets() {
            assertThat(LottoWinningType.FIFTH_PLACE.calculatePrize(3)).isEqualTo(15_000);
        }
    }

}
