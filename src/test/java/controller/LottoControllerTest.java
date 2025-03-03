package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoControllerTest {
    private LottoController lottoController;

    @BeforeEach
    void setUp() {
        lottoController = new LottoController();
    }

    @Test
    @DisplayName("구매 금액이 양수가 아니면 예외가 발생하는 지 검증한다.")
    void should_Throw_Exception_If_Negative() {
        assertThatThrownBy(() -> lottoController.validatePurchaseAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 양수여야 합니다.");
    }

    @Test
    @DisplayName("구매 금액이 1000원 단위가 아니면 예외가 발생하는 지 검증한다.")
    void should_Throw_Exception_If_Invalid_Unit() {
        assertThatThrownBy(() -> lottoController.validatePurchaseAmount(100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 1000원 단위로 입력되어야 합니다.");
    }

    @Test
    @DisplayName("구매 금액이 숫자가 아닐 경우 예외가 발생하는 지 검증한다.")
    void should_Throw_Exception_If_Not_Number() {
        assertThatThrownBy(() -> lottoController.parsePurchaseAmount("bb"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 숫자여야 합니다.");
    }

    @Test
    @DisplayName("구매 금액이 1000원 단위면 예외가 발생하지 않는 지 검증한다.")
    void should_Not_Throw_Exception_If_Valid() {
        assertThatCode(() -> lottoController.validatePurchaseAmount(3000))
                .doesNotThrowAnyException();
    }
}
