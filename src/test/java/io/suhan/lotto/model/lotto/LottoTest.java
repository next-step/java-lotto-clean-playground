package io.suhan.lotto.model.lotto;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class LottoTest {
    @Test
    void 로또는_6개의_숫자를_가진다() {
        Lotto lotto = LottoFactory.createLotto(LottoType.AUTOMATIC);

        assertThat(lotto.getNumbers()).hasSize(Lotto.LOTTO_SIZE);
    }

    @Test
    void 로또는_중복된_숫자를_가질_수_없다() {
        Lotto lotto = LottoFactory.createLotto(LottoType.AUTOMATIC);

        assertThat(lotto.getNumbers()).doesNotHaveDuplicates();
    }
}
