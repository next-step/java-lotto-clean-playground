package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import static org.junit.jupiter.api.Assertions.*;

class LottoManagerTest {

    @Test
    @DisplayName("로또 구매 테스트")
    void purchaseLottoNumbers() {
        int money = 1500;
        LottoManager lottoManager = new LottoManager();
        LottoHistory history = lottoManager.purchaseLottos(money);
        assertEquals(6, history.getLottos().get(0).getNumbers().size());
    }

    @Test
    @DisplayName("1000원 미만 입력 시 예외가 발생한다")
    void purchaseLottoslessThanMinimumthrowsException() {
        LottoManager manager = new LottoManager();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.purchaseLottos(500);
        });

        assertEquals("구입 금액의 최소단위는 1000원입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("음수 입력 시 예외가 발생한다")
    void purchaseLottosNegativeInputthrowsException() {
        LottoManager manager = new LottoManager();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.purchaseLottos(-500);
        });

        assertEquals("구입 금액은 0보다 커야 합니다.", exception.getMessage());
    }

}


