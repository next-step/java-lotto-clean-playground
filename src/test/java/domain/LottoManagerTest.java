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
        Money money = new Money(1000);
        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager lottoManager = new LottoManager(generator);
        Lottos history = lottoManager.purchaseAutoLottos(money);
        assertEquals(6, history.getLottos().get(0).getNumbers().size());
    }

    @Test
    @DisplayName("1000원 미만 입력 시 예외가 발생한다")
    void purchaseLottoslessThanMinimumthrowsException() {
        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager manager = new LottoManager(generator);

        assertThatThrownBy(() -> {
            manager.purchaseAutoLottos(new Money(500));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 1000원 단위로 입력해야 합니다.");
    }

    @Test
    @DisplayName("수동 로또 개수를 제외한 자동 로또만큼 생성된다")
    void generateAutoLottosExcludingManual() {
        Money money = new Money(5000);
        int manualCount = 2;

        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager manager = new LottoManager(generator);

        Money remainingMoney = manager.purchaseManualLottos(money, manualCount);
        Lottos autoLottos = manager.purchaseAutoLottos(remainingMoney);

        assertEquals(3, autoLottos.size());
    }

    @Test
    @DisplayName("수동 로또 수가 총 구매 가능 개수를 초과하면 예외 발생")
    void throwsExceptionWhenManualCountExceedsTotal() {
        Money money = new Money(3000);
        int manualCount = 5;

        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager manager = new LottoManager(generator);

        assertThatThrownBy(() -> manager.purchaseManualLottos(money, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 구입 금액이 부족합니다.");
    }

    @Test
    @DisplayName("수동 로또 수가 전체 구매 수와 같을 경우 자동은 0장")
    void allManualNoAuto() {
        Money money = new Money(3000);
        int manualCount = 3;

        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager manager = new LottoManager(generator);

        Money remaining = manager.purchaseManualLottos(money, manualCount);
        Lottos autoLottos = manager.purchaseAutoLottos(remaining);

        assertEquals(0, autoLottos.size());
    }

}


