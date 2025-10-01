package io.suhan.lotto.model.executor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import io.suhan.lotto.model.DrawResult;
import io.suhan.lotto.model.lotto.Lotto;
import io.suhan.lotto.model.lotto.LottoFactory;
import io.suhan.lotto.model.lotto.LottoNumber;
import io.suhan.lotto.model.lotto.LottoRegistry;
import io.suhan.lotto.model.lotto.LottoType;
import java.util.List;
import java.util.Set;
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

        Lotto winningLotto = LottoFactory.createLotto(LottoType.AUTOMATIC);
        registry.add(winningLotto);

        DrawExecutor executor = new DrawExecutor(registry, winningLotto, new LottoNumber(1));
        executor.execute();

        List<DrawResult> results = executor.getResults();

        SoftAssertions.assertSoftly((softly) -> {
            softly.assertThat(results).hasSize(1);
            softly.assertThat(results.get(0).getMatchedCount()).isEqualTo(Lotto.LOTTO_SIZE);
        });
    }

    @Test
    void 보너스_번호는_당첨번호와_중복될_수_없다() {
        LottoRegistry registry = new LottoRegistry();

        Lotto lotto = LottoFactory.createLotto(LottoType.AUTOMATIC);
        registry.add(lotto);

        Set<LottoNumber> numbers = Set.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        Lotto winningLotto = Lotto.of(numbers);

        LottoNumber bonusNumber = new LottoNumber(6);

        assertThatThrownBy(() -> {
            DrawExecutor executor = new DrawExecutor(registry, winningLotto, bonusNumber);
            executor.execute();
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
