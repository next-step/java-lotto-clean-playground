package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseCountTest {
    @Test
    void 수동_로또_개수가_0_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new PurchaseCount(1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 개수는 0개 이상이여야 합니다.");
    }

    @Test
    void 수동_로또_개수가_총_로또_개수보다_크면_예외가_발생한다() {
        assertThatThrownBy(() -> new PurchaseCount(2, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 개수는 총 로또 개수를 초과할 수 없습니다.");
    }
}
