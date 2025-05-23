package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseAmountTest {

    @Nested
    @DisplayName("PurchaseAmount 도메인 제약 테스트")
    class purchasedAmountVaildateTest {

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -1000})
        @DisplayName("구매금액이 0 이하인 경우")
        void validatePurchaseAmount_구매금액0이하_예외처리(int parchaseAmount) {

            assertThatThrownBy(() -> new PurchaseAmount(parchaseAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("금액은 0원보다 커야 합니다.");
        }
    }

    @Nested
    @DisplayName("getLottoCount() 로또 구매 개수 반환 테스트")
    class purchasedAmountToLottoCountTest {

        @ParameterizedTest
        @CsvSource({
                "1000, 1",
                "2000, 2",
                "10000000, 10000"
        })
        @DisplayName("구매 매수 테스트")
        void getLottoCount_구매금액에따른_구매매수를반환(int parchaseAmount, int expectedLottoCount) {

            assertThat(new PurchaseAmount(parchaseAmount).getLottoCount())
                    .isEqualTo(expectedLottoCount);
        }

        @ParameterizedTest
        @CsvSource({
                "1001,1",
                "9999,9",
                "1999,1"
        })
        @DisplayName("구매금액이 천원 단위가 아닌 경우 살 수 있는 만큼의 로또 개수 반환")
        void getLottoCount_구매금액천원단위가아닌경우_살수있는구매매수반환(int parchaseAmount, int expectedLottoCount) {

            assertThat(new PurchaseAmount(parchaseAmount).getLottoCount())
                    .isEqualTo(expectedLottoCount);
        }
    }

    @Nested
    @DisplayName("PurchaseAmount 필드값 테스트")
    class ReturnPurchaseAmountTest {

        @Test
        @DisplayName("PurchaseAmount 필드값 반환")
        void getPurchaseAmount() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(2000);
            assertThat(purchaseAmount.getPurchaseAmount()).isEqualTo(2000);
        }
    }
}
