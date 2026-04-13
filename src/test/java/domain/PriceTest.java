package domain;

import static domain.Price.PRICE_OF_ONE_LOTTO;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PriceTest {
    @DisplayName("가격이 priceOfOneLotto원 이상이고 priceOfOneLotto원 단위이면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 10})
    public void testPrice_ValidPrice(int count) {
        // given
        int price = count * PRICE_OF_ONE_LOTTO;

        // when & then
        assertThatCode(() -> new Price(price)).doesNotThrowAnyException();
    }

    @DisplayName("가격이 음수라면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -1000, -3000})
    public void testPrice_NegativePrice(int price) {
        // when & then
        assertThatThrownBy(() -> new Price(price)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈은 음수일 수 없습니다.");
    }

    @DisplayName("돈이 priceOfOneLotto원 이하라면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, PRICE_OF_ONE_LOTTO - 1})
    public void testPrice_NotEnoughPrice(int price) {
        // when & then
        assertThatThrownBy(() -> new Price(price)).isInstanceOf(IllegalArgumentException.class).hasMessage("돈이 부족합니다.");
    }

    @DisplayName("가격이 priceOfOneLotto원 단위가 아니라면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {PRICE_OF_ONE_LOTTO + 1, 2 * PRICE_OF_ONE_LOTTO + 500, 10 * PRICE_OF_ONE_LOTTO - 1})
    public void testPrice_IndivisiblePrice(int price) {
        // when & then
        assertThatThrownBy(() -> new Price(price)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈이 " + PRICE_OF_ONE_LOTTO + "원 단위여야 합니다.");
    }
}
