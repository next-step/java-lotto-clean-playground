package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @DisplayName("구입 금액이 1000원 미만이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 500, 999})
    void 구입_금액이_1000원_미만이면_예외가_발생한다(int amount) {
        assertThrows(IllegalArgumentException.class, () -> new Money(amount));
    }

    @DisplayName("구입 금액이 1000원 이상이면 로또 개수를 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 1500, 2000})
    void 구입_금액이_1000원_이상이면_로또_개수를_반환한다(int amount) {
        Money money = new Money(amount);
        int numberOfLottos = money.calculateNumberOfLottos();

        assertThat(numberOfLottos).isEqualTo(amount / 1000);
    }


    @DisplayName("수동 구매 개수가 총 구매 개수를 초과하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {3,4})
    void 수동_구매_개수가_총_구매_개수를_초과하면_예외가_발생한다(int manualCount) {
        Money money = new Money(2000); // 2개의 로또를 구매할 수 있음
        assertThrows(IllegalArgumentException.class, () -> money.validateManualCount(manualCount));
    }


    @DisplayName("수동 구매 개수가 총 구매 개수를 초과하지 않으면 예외가 발생하지 않는다")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void 수동_구매_개수가_총_구매_개수를_초과하면_예외가_발생하지_않는다(int manualCount) {
        Money money = new Money(2000); // 2개의 로또를 구매할 수 있음
        assertDoesNotThrow(() -> money.validateManualCount(manualCount));
    }

}
