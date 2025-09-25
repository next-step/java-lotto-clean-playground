package io.suhan.lotto.model.lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class LottoNumberTest {
    @Test
    void 유효한_번호를_생성할_수_있다() {
        LottoNumber number = new LottoNumber(Lotto.LOTTO_NUMBER_MIN);
        assertEquals(Lotto.LOTTO_NUMBER_MIN, number.getValue());
    }

    @Test
    void 번호는_범위를_벗어날_수_없다() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(Lotto.LOTTO_NUMBER_MIN - 1));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(Lotto.LOTTO_NUMBER_MAX + 1));
    }
}
