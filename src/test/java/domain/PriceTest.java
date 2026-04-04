package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PriceTest {
    @DisplayName("가격이 1000원 이상이고 1000원 단위이면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000, 10000})
    public void testPrice_ValidPrice(int price) {
        // when & then
        assertThatCode(() -> new Price(price)).doesNotThrowAnyException();
    }

    @DisplayName("가격이 음수라면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -1000, -2000, -3000})
    public void testPrice_NegativePrice(int price) {
        // when & then
        assertThatThrownBy(() -> new Price(price)).isInstanceOf(IllegalArgumentException.class).hasMessage("돈은 음수일 수 없습니다.");
    }

    @DisplayName("돈이 1000원 이하라면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 50, 200, 999})
    public void testPrice_NotEnoughPrice(int price) {
        // when & then
        assertThatThrownBy(() -> new Price(price)).isInstanceOf(IllegalArgumentException.class).hasMessage("돈이 부족합니다.");
    }

    @DisplayName("가격이 1000원 단위가 아니라면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {1001, 20050, 3200, 9999})
    public void testPrice_IndivisiblePrice(int price) {
        // when & then
        assertThatThrownBy(() -> new Price(price)).isInstanceOf(IllegalArgumentException.class).hasMessage("돈이 1000원 단위여야 합니다.");
    }
}
