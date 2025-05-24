package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoViewTest {

    @Test
    @DisplayName("구입 금액에 숫자가 아닌 값 입력 시 예외 발생")
    void readPurchaseAmount_invalidInput() {
        // given
        Scanner testScanner = new Scanner("만원\n");
        LottoView view = new LottoView(testScanner);

        // when & then
        assertThatThrownBy(view::readPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자를 입력하세요");
    }

    @Test
    @DisplayName("당첨 번호가 6개 미만일 때 예외 발생")
    void readWinningNumbers_invalidCount() {
        // given
        Scanner testScanner = new Scanner("1,2,4\n");
        LottoView view = new LottoView(testScanner);

        // when & then
        assertThatThrownBy(view::readWinningNumbers)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개를 입력해야 합니다.");
    }

    @Test
    @DisplayName("수동 로또 번호가 6개 미만일 경우 예외 발생")
    void readManualLottos_invalidCount() {
        // given
        Scanner testScanner = new Scanner("1,2,4\n");
        LottoView view = new LottoView(testScanner);

        // when & then
        assertThatThrownBy(() -> view.readManualLottos(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개를 입력해야 합니다.");
    }
}
