package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCountTest {

    @Test
    @DisplayName("구매 개수는 0 이상이어야 한다")
    void 구매_개수는_0_이상이어야_한다() {
        LottoCount count = new LottoCount(0);
        assertThat(count.getCount()).isZero();
    }

    @Test
    @DisplayName("구매 개수가 음수이면 예외가 발생한다")
    void 구매_개수가_음수이면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> {new LottoCount(-1);});
    }

    @Test
    @DisplayName("두 구매 개수의 차이를 정확히 계산한다")
    void 두_구매_개수의_차이를_정확히_계산한다() {
        LottoCount totalCount = new LottoCount(5);
        LottoCount manualCount = new LottoCount(2);

        LottoCount autoCount = totalCount.subtract(manualCount);

        assertThat(autoCount.getCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("차감할 개수가 현재 개수보다 크면 예외가 발생한다")
    void 차감할_개수가_현재_개수보다_크면_예외가_발생한다() {
        LottoCount totalCount = new LottoCount(5);
        LottoCount manualCount = new LottoCount(6);

        assertThrows(IllegalArgumentException.class, () -> {
            totalCount.subtract(manualCount);});
    }
}
