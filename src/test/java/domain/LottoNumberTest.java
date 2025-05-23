package domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @Nested
    @DisplayName("정적 팩토리 메서드 테스트")
    class LottoNumberOfTest {

        @Nested
        @DisplayName("LottoNumber 범위 도메인 제약 테스트")
        class LottoNumberValidateTest {

            @ParameterizedTest
            @ValueSource(ints = {1, 8, 13, 33, 45})
            @DisplayName("정상적인 범위 내의 숫자인 경우")
            void LottoNumberOf_정상적인범위내숫자는_예외를발생하지않는다(int number) {
                assertThatNoException().isThrownBy(() -> LottoNumber.of(number));
            }

            @ParameterizedTest
            @ValueSource(ints = {-1, 0, 46, 100})
            @DisplayName("제약에 어긋난 숫자인 경우")
            void LottoNumberOf_제약조건에어긋난숫자는_예외를발생한다(int number) {
                assertThatThrownBy(() -> LottoNumber.of(number))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("로또 번호는 1부터 45 사이어야 합니다.");
            }
        }

        @Nested
        @DisplayName("같은 숫자인 경우 같은 인스턴스를 반환")
        class LottoNumberReturnObjectTest {

            @Test
            @DisplayName("같은 값은 같은 인스턴스인지 테스트")
            void LottoNumberOf_같은숫자는_같은인스턴스를반환한다() {
                LottoNumber number1 = LottoNumber.of(1);
                LottoNumber number2 = LottoNumber.of(1);

                assertThat(number1).isSameAs(number2);
            }
        }
    }
    @Nested
    @DisplayName("LottoNumber 필드값 테스트")
    class ReturnPurchaseAmountTest {

        @Test
        @DisplayName("PurchaseAmount 필드값 반환")
        void getPLottoNumber() {
            LottoNumber lottoNumber = LottoNumber.of(33);
            assertThat(lottoNumber.getLottoNumber()).isEqualTo(33);
        }
    }
}
