package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMachineTest {

    @Test
    @DisplayName("투입 금액에 맞는 로또 수량을 정확히 계산한다.")
    void calculateTicketCountTest() {
        // given
        Money money = new Money(5000);

        // when
        int count = LottoMachine.calculateTicketCount(money);

        // then
        assertThat(count).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {500, 1500, 0})
    @DisplayName("금액이 1000원 단위가 아니거나 최소 금액 미만이면 예외가 발생한다.")
    void validateLottoUnit(int amount) {
        // given
        Money money = new Money(amount);

        // when // then
        assertThatThrownBy(() -> LottoMachine.calculateTicketCount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 1000원 단위로만 구매 가능합니다.");
    }

    @Test
    @DisplayName("수동 구매 횟수가 전체 구매 가능 횟수 이내라면 예외가 발생하지 않는다.")
    void validateManualCountSuccessTest() {
        // given
        int totalCount = 10;
        int manualCount = 5;

        // when // then
        LottoMachine.validateManualCount(totalCount, manualCount);
    }

    @Test
    @DisplayName("수동 구매 횟수가 전체 구매 가능 횟수를 초과하면 예외가 발생한다.")
    void validateManualCountFailTest() {
        // given
        int totalCount = 5;
        int manualCount = 6;

        // when // then
        assertThatThrownBy(() -> LottoMachine.validateManualCount(totalCount, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력하신 수동 구매 횟수가 뽑을 수 있는 로또 수를 넘어섰습니다.");
    }
}
