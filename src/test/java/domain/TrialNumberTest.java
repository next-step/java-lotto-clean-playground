package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TrialNumberTest {

    @DisplayName("구입 금액이 1000원 단위일 경우 정상적으로 시도 횟수를 반환한다.")
    @Test
    void createTrialNumber_ValidAmount() {
        TrialNumber trialNumber = new TrialNumber(14000);
        assertThat(trialNumber.getTrialNumber()).isEqualTo(14);
        assertThat(trialNumber.getPurchaseAmount()).isEqualTo(14000);
    }

    @DisplayName("구입 금액이 0 이하일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    void createTrialNumber_ZeroOrNegative(int invalidAmount) {
        assertThatThrownBy(() -> new TrialNumber(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 0보다 커야 합니다.");
    }

    @DisplayName("구입 금액이 1000원 단위가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1500, 100})
    void createTrialNumber_NotDivisibleBy1000(int invalidAmount) {
        assertThatThrownBy(() -> new TrialNumber(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
    }
}