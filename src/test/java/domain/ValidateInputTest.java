package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ValidateInputTest {

    @ParameterizedTest
    @ValueSource(strings = {"abc", "12a", " "})
    @DisplayName("숫자가 아닌 값이 입력되면 예외가 발생한다.")
    void validateNumberTest(String input) {
        //given //when //then
        assertThatThrownBy(() -> ValidateInput.validateNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 숫자여야 합니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    @DisplayName("입력값이 비어있거나 공백이면 예외가 발생한다.")
    void validateEmptyTest(String input) {
        //given //when //then
        assertThatThrownBy(() -> ValidateInput.validateEmpty(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값이 비어 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a, b, c", "1, 2, 3.5", "1,,3", "1, ,3"})
    @DisplayName("로또 번호 형식이 숫자가 아니거나 비어있으면 예외가 발생한다.")
    void validateLottoTest(String input) {
        //given //when //then
        assertThatThrownBy(() -> ValidateInput.validateLotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 숫자여야 하며, 각 번호는 쉼표(,)로 구분되어야 합니다.");
        // 클래스의 throw new IllegalArgumentException 메시지와 일치시켜야 함
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void validateBonusNumberTest() {
        //given
        Lotto numbers = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoNumber bonusNumber = new LottoNumber(3);

        //when //then
        assertThatThrownBy(() -> ValidateInput.validateBonusNumber(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼의 숫자가 지난 주 당첨 번호와 중복됩니다.");
    }

    @Test
    @DisplayName("수동 구매 횟수가 전체 구매 가능 횟수를 초과하면 예외가 발생한다.")
    void validateManualCountTest() {
        //given
        int totalCount = 5;
        int manualCount = 6;

        //when //then
        assertThatThrownBy(() -> ValidateInput.validateManualCount(totalCount, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력하신 수동 구매 횟수가 뽑을 수 있는 로또 수를 넘어섰습니다.");
    }
}
