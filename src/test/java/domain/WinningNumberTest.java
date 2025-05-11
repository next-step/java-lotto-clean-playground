package domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class WinningNumberTest {

    @Nested
    @DisplayName("생성 성공 케이스")
    class SuccessCases {
        @Test
        @DisplayName("1부터 45 사이의 값으로 생성할 수 있다")
        void createWithValidRange() {
            assertThatCode(() -> new WinningNumber(1)).doesNotThrowAnyException();
            assertThatCode(() -> new WinningNumber(45)).doesNotThrowAnyException();
            assertThatCode(() -> new WinningNumber(23)).doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("생성 실패 케이스")
    class FailureCases {
        @Test
        @DisplayName("0보다 작거나 46보다 크면 예외가 발생한다")
        void throwIfOutOfRange() {
            SoftAssertions softly = new SoftAssertions();

            softly.assertThatThrownBy(() -> new WinningNumber(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");

            softly.assertThatThrownBy(() -> new WinningNumber(46))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");

            softly.assertAll();
        }
    }
}
