package org.duckstudy.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("가격 테스트")
class PriceTest {

    @Nested
    @DisplayName("가격 검증 테스트")
    class PriceValidationTest {

        @Test
        @DisplayName("가격이 0 이상일 경우 성공한다")
        void validateSuccessWhenPriceIsEqualOrGreaterThanZero() {
            assertThatCode(() -> new Price(1))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("가격이 0보다 작을 경우 예외가 발생한다")
        void validateFailWhenPriceIsLessThanZero() {
            assertThatThrownBy(() -> new Price(-1))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("가격은 0원 이상이어야 합니다.");
        }
    }

    @Nested
    @DisplayName("가격 계산 테스트")
    class PriceCalculateTest {

        @Test
        @DisplayName("로또 구매 금액을 입력하면 가격에 맞는 로또 개수를 계산한다")
        void calculateLottoCountWhenInputPrice() {
            Price price = new Price(10000);

            assertThat(price.calculateLottoCount()).isEqualTo(10);
        }
    }
}
