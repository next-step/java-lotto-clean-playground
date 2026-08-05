package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("LottoTicketCount 클래스")
class LottoTicketCountTest {

    @Nested
    @DisplayName("정상적인 금액 입력 시")
    class ValidAmount {
        @DisplayName("구매 금액을 로또 티켓 수로 변환한다.")
        @ParameterizedTest
        @CsvSource({
                "14000, 14",
                "1000, 1",
                "2000, 2"
        })
        void shouldConvertPriceToTicketCountCorrectly(int price, int expectedCount) {
            int actualCount = LottoTicketCount.convertLottoPriceToTicketCount(price);

            assertThat(actualCount).isEqualTo(expectedCount);
        }
    }

    @Nested
    @DisplayName("유효하지 않은 금액 입력 시")
    class InvalidAmount {

        @DisplayName("1000원 미만일 경우 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(ints = {0, 100, 999})
        void shouldThrowExceptionForAmountLessThan1000(int price) {
            assertThatThrownBy(() -> LottoTicketCount.convertLottoPriceToTicketCount(price))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("구입 금액은 " + LottoTicketCount.PRICE_PER_ONE_LOTTO_TICKET + "원 이상이어야 합니다.");
        }

        @DisplayName("1000원 단위가 아닐 경우 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(ints = {1001, 1500, 2999})
        void shouldThrowExceptionForAmountNotMultipleOf1000(int price) {
            assertThatThrownBy(() -> LottoTicketCount.convertLottoPriceToTicketCount(price))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("구입 금액은 " + LottoTicketCount.PRICE_PER_ONE_LOTTO_TICKET + "원 단위로 입력해야 합니다.");
        }
    }
}
