package org.duckstudy.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("로또 개수 테스트")
class LottoCountTest {

    @Nested
    @DisplayName("로또 개수 생성 테스트")
    class LottoCountCreationTest {

        @Test
        @DisplayName("로또 개수를 입력하면 로또 개수를 생성한다")
        public void createLottoCountWhenInputCount() {

            assertThatCode(() -> new LottoCount(10))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("로또 개수가 음수일 경우 예외가 발생한다")
        public void createLottoCountFailWhenCountIsLessThanZero() {

            assertThatThrownBy(() -> new LottoCount(-1))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 개수는 0개 이상이어야 합니다.");
        }
    }

    @Nested
    @DisplayName("수동 로또 개수 검증 테스트")
    class ManualLottoCountValidationTest {

        @Test
        @DisplayName("수동으로 구매할 로또 수가 전체 로또 수를 초과하지 않으면 성공한다")
        public void validateManualLottoCountSuccessWhenManualLottoCountIsNotGreaterThanTotalLottoCount() {

            LottoCount manualLottoCount = new LottoCount(10);
            LottoCount totalLottoCount = new LottoCount(15);

            assertThatCode(() -> manualLottoCount.validateManualLottoCount(totalLottoCount))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("수동으로 구매할 로또 수가 전체 로또 수를 초과하면 예외가 발생한다")
        public void validateManualLottoCountFailWhenManualLottoCountIsGreaterThanTotalLottoCount() {

            LottoCount manualLottoCount = new LottoCount(20);
            LottoCount totalLottoCount = new LottoCount(15);

            assertThatThrownBy(() -> manualLottoCount.validateManualLottoCount(totalLottoCount))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("수동으로 구매할 로또 수가 전체 로또 수를 초과합니다.\n");
        }
    }

    @Nested
    @DisplayName("로또 개수 계산 테스트")
    class LottoCountCalculateTest {

        @Test
        @DisplayName("로또 개수를 뺀다")
        public void subtractLottoCount() {

            LottoCount lottoCount = new LottoCount(10);

            LottoCount result = lottoCount.subtract(new LottoCount(5));

            assertThat(result).isEqualTo(new LottoCount(5));
        }
    }
}
