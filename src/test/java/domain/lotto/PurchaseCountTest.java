package domain.lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseCountTest {

    @Test
    void 수동_개수가_전체_개수_이하이면_생성된다() {
        assertDoesNotThrow(() -> new PurchaseCount(5, 3));
    }

    @Test
    void 수동_개수가_음수면_예외가_발생한다() {
        assertThatThrownBy(() -> new PurchaseCount(5, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수는 0 이상이어야 합니다.");
    }

    @Test
    void 수동_개수가_전체_개수를_초과하면_예외가_발생한다() {
        assertThatThrownBy(() -> new PurchaseCount(1, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동으로 구매 가능한 개수를 초과했습니다.");
    }

    @Test
    void 자동_구매_개수는_전체에서_수동을_뺀_값이다() {
        PurchaseCount purchaseCount = new PurchaseCount(5, 2);

        int result = purchaseCount.autoCount();

        assertEquals(3, result);
    }
}
