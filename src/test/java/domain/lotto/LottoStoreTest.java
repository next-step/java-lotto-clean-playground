package domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoStoreTest {
    private Lotto createLotto(int... numbers) {
        return new Lotto(
                Arrays.stream(numbers)
                        .mapToObj(LottoNumber::from)
                        .toList()
        );
    }

    @Test
    void 구매금액이_정확히_1000원이면_로또가_1장_생성된다() {
        int totalCount = LottoStore.calculatePurchasableCount(Money.from(1000));

        Lottos lottos = LottoStore.buy(totalCount, List.of());

        assertEquals(1, lottos.size());
    }

    @Test
    void 구매금액만큼_로또를_생성한다() {
        int totalCount = LottoStore.calculatePurchasableCount(Money.from(5000));

        Lottos lottos = LottoStore.buy(totalCount, List.of());

        assertEquals(5, lottos.size());
    }

    @Test
    void 구매금액이_1000원보다_적을_때_예외가_발생한다() {
        assertThatThrownBy(() -> LottoStore.calculatePurchasableCount(Money.from(0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 1000원 이상이어야 합니다.");
    }

    @Test
    void 수동_로또와_자동_로또를_합친_갯수를_반환한다() {
        Lotto manualLotto = createLotto(1, 2, 3, 4, 5, 6);
        int totalCount = LottoStore.calculatePurchasableCount(Money.from(3000));

        Lottos lottos = LottoStore.buy(totalCount, List.of(manualLotto));

        assertEquals(3, lottos.size());
    }

    @Test
    void 수동_개수가_구매_가능_개수를_초과하면_예외가_발생한다() {
        Lotto manualLotto1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto manualLotto2 = createLotto(7, 8, 9, 10, 11, 12);
        int totalCount = LottoStore.calculatePurchasableCount(Money.from(1000));

        assertThatThrownBy(() ->
                LottoStore.buy(totalCount, List.of(manualLotto1, manualLotto2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수가 구매 가능한 개수를 초과했습니다.");
    }

    @Test
    void 수동_개수가_음수면_예외가_발생한다() {
        assertThatThrownBy(() -> LottoStore.validateManualCount(1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수는 0 이상이어야 합니다.");
    }

    @Test
    void 수동_개수가_전체_구매_개수를_초과하면_검증에서_예외가_발생한다() {
        assertThatThrownBy(() -> LottoStore.validateManualCount(1, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동으로 구매 가능한 개수를 초과했습니다.");
    }
}
