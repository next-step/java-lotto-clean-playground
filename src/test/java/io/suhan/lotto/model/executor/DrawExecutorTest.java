package io.suhan.lotto.model.executor;

import io.suhan.lotto.model.DrawResult;
import io.suhan.lotto.model.lotto.Lotto;
import io.suhan.lotto.model.lotto.LottoFactory;
import io.suhan.lotto.model.lotto.LottoRegistry;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class DrawExecutorTest {
    @Test
    void 당첨번호와_로또를_비교할_수_있다() {
        LottoRegistry registry = new LottoRegistry();

        Lotto winningLotto = LottoFactory.createLotto();
        registry.add(winningLotto);

        DrawExecutor executor = new DrawExecutor(registry, winningLotto);
        executor.execute();

        List<DrawResult> results = executor.getResults();

        SoftAssertions.assertSoftly((softly) -> {
            softly.assertThat(results).hasSize(1);
            softly.assertThat(results.get(0).getMatchedCount()).isEqualTo(Lotto.LOTTO_SIZE);
        });
    }
}
