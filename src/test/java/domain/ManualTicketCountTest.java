package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ManualTicketCountTest {
    @DisplayName("개수가 0 이상이고 금액 범위 내라면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource
    public void testManualTicketCount_ValidCountAndPrice(int count, Price price) {
        // when & then
        assertThatCode(() -> new ManualTicketCount(count, price)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> testManualTicketCount_ValidCountAndPrice() {
        return Stream.of(
                Arguments.arguments(0, new Price(1000)),
                Arguments.arguments(1, new Price(3000)),
                Arguments.arguments(3, new Price(3000))
        );
    }

    @DisplayName("개수가 0 미만이면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @MethodSource
    public void testManualTicketCount_InvalidCount(int count, Price price) {
        // when & then
        assertThatThrownBy(() -> new ManualTicketCount(count, price)).isInstanceOf(IllegalArgumentException.class).hasMessage("개수가 음수일 수 없습니다.");
    }

    private static Stream<Arguments> testManualTicketCount_InvalidCount() {
        return Stream.of(
                Arguments.arguments(-1, new Price(1000)),
                Arguments.arguments(-3, new Price(3000)),
                Arguments.arguments(-4, new Price(3000))
        );
    }

    @DisplayName("입력 개수가 금액에 상당하는 개수보다 많으면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @MethodSource
    public void testManualTicketCount_CountOverBudget(int count, Price price) {
        // when & then
        assertThatThrownBy(() -> new ManualTicketCount(count, price)).isInstanceOf(IllegalArgumentException.class).hasMessage("해당 개수만큼 생성하기에는 돈이 부족합니다.");
    }

    private static Stream<Arguments> testManualTicketCount_CountOverBudget() {
        return Stream.of(
                Arguments.arguments(5, new Price(1000)),
                Arguments.arguments(4, new Price(3000)),
                Arguments.arguments(6, new Price(3000))
        );
    }
}
