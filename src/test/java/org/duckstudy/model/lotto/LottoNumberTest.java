package org.duckstudy.model.lotto;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 번호 테스트")
class LottoNumberTest {

    @Nested
    @DisplayName("로또 번호 생성 테스트")
    class LottoCreationTest {

        @Test
        @DisplayName("1 이상 45 이하의 숫자를 가진 로또 번호를 생성한다")
        void createLottoNumberSuccess() {

            assertThatCode(() -> LottoNumber.valueOf(1))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 46})
        @DisplayName("1 미만의 숫자이거나 45 초과의 숫자를 가진 로또 번호를 생성하면 예외를 발생한다")
        void createLottoNumberFail(int number) {

            assertThatThrownBy(() -> LottoNumber.valueOf(number))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1 이상 45 이하의 숫자여야 합니다.");
        }
    }
}
