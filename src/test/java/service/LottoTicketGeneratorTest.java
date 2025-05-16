package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class LottoTicketGeneratorTest {

    private final LottoTicketGenerator generator = new LottoTicketGenerator();

    @ParameterizedTest(name = "[{index}] 금액: {0}원 -> {1}장")
    @CsvSource({
            "1000, 1",
            "2000, 2",
            "999, 0",
            "0, 0",
            "1999, 1"
    })
    @DisplayName("금액에 따라 로또 티켓 수를 계산한다")
    void generateTicketsBasedOnAmount(int amount, int expectedTicketCount) {
        assertThat(generator.generate(amount)).isEqualTo(expectedTicketCount);
    }

    @Test
    @DisplayName("음수 금액으로는 예외가 발생한다")
    void generateThrowsExceptionForNegativeAmount() {
        assertThatThrownBy(() -> generator.generate(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 음수일 수 없습니다.");
    }
}
