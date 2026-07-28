import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @Nested
    @DisplayName("로또 번호 생성 테스트")
    class create {

        @ParameterizedTest
        @ValueSource(ints = {1, 23, 45})
        @DisplayName("1부터 45 사이면 생성 성공 테스트")
        void 범위_안이면_생성_성공(int value) {
            LottoNumber lottoNumber = LottoNumber.of(value);

            assertThat(lottoNumber.getValue()).isEqualTo(value);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 46})
        @DisplayName("1부터 45 범위를 벗어나면 예외 발생 테스트")
        void 범위_밖이면_예외_발생(int value) {
            String throwMessage = "로또 번호는 1부터 45 사이여야 합니다.";

            assertThatThrownBy(() -> LottoNumber.of(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }
    }
}
