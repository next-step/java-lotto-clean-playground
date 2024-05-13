package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BudgetInfoTest {
    @Test
    @DisplayName("구매 금액은 숫자로만 입력해야 한다.")
    public void 구매_금액은_숫자로만_입력() {
        assertThrows(NumberFormatException.class, this::causeExceptionType);
    }

    private void causeExceptionType() {
        InputView inputView = new InputView();
        BudgetInfo.valueOf("10원");
    }

    @Test
    @DisplayName("구매 금액은 0원 이상이다.")
    public void 구매_금액은_양수로만_입력() {
        assertThatThrownBy(() -> {
            causeExceptionRange();
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 구매 금액은 0원 이상이어야 합니다.");
    }

    private void causeExceptionRange() {
        InputView inputView = new InputView();
        BudgetInfo.valueOf("-10000");
    }

    @Test
    @DisplayName("구매 금액은 1000원 단위이다.")
    public void 구매_금액은_1000원_단위로_입력() {
        assertThatThrownBy(() -> {
            causeExceptionUnit();
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 구매 금액은 1000원 단위여야 합니다.");
    }

    private void causeExceptionUnit() {
        InputView inputView = new InputView();
        BudgetInfo.valueOf("12345");
    }
}