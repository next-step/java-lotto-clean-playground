package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoStoreTest {
    @Test
    void 구매금액이_정확히_1000원이면_로또가_1장_생성된다() {
        // Given
        LottoStore lottoStore = new LottoStore();
        // When
        Lottos lottos = lottoStore.buy(new Money(1000));
        // Then
        assertEquals(1, lottos.size());
    }

    @Test
    void 구매금액만큼_로또를_생성한다() {
        // Given
        LottoStore lottoStore = new LottoStore();
        // When
        Lottos lottos = lottoStore.buy(new Money(5000));
        // Then
        assertEquals(5, lottos.size());
    }

    @Test
    void 구매금액이_1000원_단위가_아니면_예외가_발생한다() {
        // Given
        LottoStore lottoStore = new LottoStore();
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lottoStore.buy(new Money(1500)));
        assertEquals("구매 금액은 1000원 단위여야 합니다.", exception.getMessage());
    }

    @Test
    void 구매금액이_1000원보다_적을_때_예외가_발생한다() {
        // Given
        LottoStore lottoStore = new LottoStore();
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lottoStore.buy(new Money(999))
        );
        assertEquals("구매 금액은 1000원 이상이어야 합니다.", exception.getMessage());
    }
}
