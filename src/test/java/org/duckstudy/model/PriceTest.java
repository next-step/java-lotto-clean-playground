package org.duckstudy.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;
import org.duckstudy.model.lotto.LottoResult;
import org.duckstudy.model.lotto.constant.WinningRank;
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
        @DisplayName("가격을 더한다")
        void addPrice() {

            Price price = new Price(1000);

            Price result = price.addPrice(2000);

            assertThat(result).isEqualTo(new Price(3000));
        }

        @Test
        @DisplayName("가격을 곱한다")
        void multiplyPrice() {

            Price price = new Price(1000);

            Price result = price.multiplyTimes(3);

            assertThat(result).isEqualTo(new Price(3000));
        }

        @Test
        @DisplayName("가격을 나눈다")
        void dividePriceByPrice() {

            Price price = new Price(1000);

            double result = price.divideByPrice(new Price(10));

            assertThat(result).isEqualTo(100);
        }

        @Test
        @DisplayName("가격을 0원으로 나누면 예외가 발생한다")
        void dividePriceByZeroPrice() {

            Price price = new Price(1000);

            assertThatThrownBy(() -> price.divideByPrice(new Price(0)))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("0으로 나눌 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("로또 계산 테스트")
    class LottoCalculationTest {

        @Test
        @DisplayName("로또 구매 금액을 입력하면 가격에 맞는 로또 개수를 계산한다")
        void calculateLottoCountWhenInputPrice() {

            Price price = new Price(10000);

            assertThat(price.calculateLottoCount()).isEqualTo(10);
        }

        @Test
        @DisplayName("로또 수익률을 계산한다")
        void calculateProfitRate() {
            Price purchasePrice = new Price(15000);
            Map<Integer, Integer> result = new HashMap<>();
            result.put(WinningRank.FIRST.getKey(), 1);
            LottoResult lottoResult = new LottoResult(result);

            assertThat(purchasePrice.calculateProfitRate(lottoResult)).isEqualTo(1.3333333333333334E7);
        }
    }
}
