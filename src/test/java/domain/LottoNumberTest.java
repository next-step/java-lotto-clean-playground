package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Errors;

class LottoNumberTest {

    @ParameterizedTest(name = "{0}은 로또 범위 안의 숫자이므로 정상적으로 로또 번호가 생성된다.")
    @ValueSource(ints = {1, 45, 10, 15})
    @DisplayName("로또 범위 안의 숫자로 로또 번호를 만들 수 있다.")
    void createTest(int lottoNumberValue) {
        //  given
        // when
        final LottoNumber lottoNumber = new LottoNumber(lottoNumberValue);
        // then
        assertThat(lottoNumber.getValue())
            .isEqualTo(lottoNumberValue);
    }

    @ParameterizedTest(name = "{0}은 로또 범위 밖의 숫자이므로 예외가 발생한다.")
    @ValueSource(ints = {0, -1, 48, 50})
    @DisplayName("로또 범위 밖의 숫자로 로또 번호를 만들면 예외가 발생한다.")
    void invalidCreateTest(int lottoNumberValue) {
        //  given
        // when
        // then
        assertThatThrownBy(() -> new LottoNumber(lottoNumberValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.NUMBER_IS_NOT_IN_VALID_RANGE);
    }

    @Nested
    @DisplayName("compareTo 테스트")
    class CompareToTest {

        @Test
        public void sameValue() {
            // given
            LottoNumber number1 = new LottoNumber(5);
            LottoNumber number2 = new LottoNumber(5);
            // when
            final int result = number1.compareTo(number2);
            assertThat(0)
                .isEqualTo(result);
        }

        @Test
        public void differentValue() {
            // given
            LottoNumber number1 = new LottoNumber(5);
            LottoNumber number2 = new LottoNumber(10);
            // when
            final boolean result1 = number1.compareTo(number2) < 0;
            final boolean result2 = number2.compareTo(number1) > 0;
            // then
            assertTrue(result1);
            assertTrue(result2);
        }

        @Test
        public void invalidCase() {
            // given
            LottoNumber number = new LottoNumber(5);
            final BigInteger i = new BigInteger("10");
            // when
            // then
            assertThatThrownBy(() -> number.compareTo(i))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Errors.SORT_LOTTO_NUMBER_ERROR);
        }
    }

    @Nested
    @DisplayName("equalTo 테스트")
    class EqualToTest {

        @Test
        public void sameValue() {
            // given
            LottoNumber number1 = new LottoNumber(5);
            LottoNumber number2 = new LottoNumber(5);
            // when
            //then
            assertEquals(number1, number2);
        }

        @Test
        public void differentValue() {
            // given
            LottoNumber number1 = new LottoNumber(5);
            LottoNumber number2 = new LottoNumber(10);
            // when
            // then
            assertNotEquals(number1, number2);
        }

        @Test
        public void nullCase() {
            // given
            LottoNumber number = new LottoNumber(5);
            // when
            // then
            assertNotEquals(null, number);
        }

        @Test
        public void differentClass() {
            // given
            LottoNumber number = new LottoNumber(5);
            final BigInteger bigInteger = new BigInteger("5");
            // when
            // then
            assertNotEquals(number, bigInteger);
        }
    }

    @Nested
    @DisplayName("hashCode 테스트")
    class HashCodeTest {

        @Test
        public void sameValue() {
            // given
            LottoNumber number1 = new LottoNumber(5);
            LottoNumber number2 = new LottoNumber(5);
            // when
            final int hashCode1 = number1.hashCode();
            final int hashCode2 = number2.hashCode();
            // then
            assertEquals(hashCode1, hashCode2);
        }

        @Test
        public void differentValue() {
            // given
            LottoNumber number1 = new LottoNumber(5);
            LottoNumber number2 = new LottoNumber(10);
            // when
            final int hashCode1 = number1.hashCode();
            final int hashCode2 = number2.hashCode();
            // then
            assertNotEquals(hashCode1, hashCode2);
        }
    }

}
