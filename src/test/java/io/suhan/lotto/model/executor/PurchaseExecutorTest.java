package io.suhan.lotto.model.executor;

import static io.suhan.lotto.model.executor.PurchaseExecutor.PRICE_PER_LOTTO;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import io.suhan.lotto.model.lotto.LottoRegistry;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class PurchaseExecutorTest {
    @Test
    void 금액에_맞는_로또를_구매할_수_있다() {
        LottoRegistry registry = new LottoRegistry();
        int balance = 5000;

        PurchaseExecutor executor = new PurchaseExecutor(registry, balance);
        executor.execute();

        int expectedSize = balance / PRICE_PER_LOTTO;
        assertThat(registry.getLottos()).hasSize(expectedSize);
    }
}
