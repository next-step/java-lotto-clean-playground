package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.lotto.Lottos;
import domain.lotto.ManualPurchaseCount;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {
    private final InputStream standardInput = System.in;

    @AfterEach
    void restoreInput() {
        System.setIn(standardInput);
    }

    @Test
    @DisplayName("수동 구매 수를 입력받는다")
    void readManualPurchaseCount() {
        System.setIn(new ByteArrayInputStream("3".getBytes()));
        InputView inputView = new InputView();

        ManualPurchaseCount manualPurchaseCount = inputView.readManualPurchaseCount();

        assertThat(manualPurchaseCount.value()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 구매 로또 번호를 입력받는다")
    void readManualLottos() {
        System.setIn(new ByteArrayInputStream("1,2,3,4,5,6\n7,8,9,10,11,12".getBytes()));
        InputView inputView = new InputView();

        Lottos manualLottos = inputView.readManualLottos(ManualPurchaseCount.from(2));

        assertThat(manualLottos.values()).hasSize(2);
        assertThat(manualLottos.values().get(0).values()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("수동 구매 번호가 숫자 형식이 아니면 예외가 발생한다")
    void throwExceptionWhenManualNumbersAreNotNumbers() {
        System.setIn(new ByteArrayInputStream("1 2 3 4 5 6".getBytes()));
        InputView inputView = new InputView();

        assertThatThrownBy(() -> inputView.readManualLottos(ManualPurchaseCount.from(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 번호는 쉼표(,)로 구분한 숫자여야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다")
    void throwExceptionWhenPurchaseAmountIsNotNumber() {
        System.setIn(new ByteArrayInputStream("abc".getBytes()));
        InputView inputView = new InputView();

        assertThatThrownBy(inputView::readPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 숫자여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호가 숫자 형식이 아니면 예외가 발생한다")
    void throwExceptionWhenWinningNumbersAreNotNumbers() {
        System.setIn(new ByteArrayInputStream("1 4 8 33 42 45".getBytes()));
        InputView inputView = new InputView();

        assertThatThrownBy(inputView::readWinningLotto)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 쉼표(,)로 구분한 숫자여야 합니다.");
    }

    @Test
    @DisplayName("보너스 볼이 숫자가 아니면 예외가 발생한다")
    void throwExceptionWhenBonusBallIsNotNumber() {
        System.setIn(new ByteArrayInputStream("1,2,3,4,5,6\nabc".getBytes()));
        InputView inputView = new InputView();

        assertThatThrownBy(inputView::readWinningLotto)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 숫자여야 합니다.");
    }
}
