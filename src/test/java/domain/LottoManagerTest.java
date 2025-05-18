package domain;

import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoManagerTest {

    @Test
    @DisplayName("로또 구매 테스트")
    void purchaseLottoNumbers() {
        int money = 1500;
        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager lottoManager = new LottoManager(generator);
        LottoHistory history = lottoManager.purchaseLottos(money);
        assertEquals(6, history.getLottos().get(0).getNumbers().size());
    }

    @Test
    @DisplayName("1000원 미만 입력 시 예외가 발생한다")
    void purchaseLottoslessThanMinimumthrowsException() {
        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager manager = new LottoManager(generator);

        assertThatThrownBy(() -> {
            manager.purchaseLottos(500);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액의 최소단위는 1000원입니다.");
    }

}


