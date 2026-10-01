package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
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
}
