package org.duckstudy.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.duckstudy.model.lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또 테스트")
class LottoTest {

    @Test
    @DisplayName("1 이상 45 이하의 숫자 6개를 가진 로또를 생성한다")
    void createLotto() {
        Lotto lotto = new Lotto();

        assertAll(
                () -> assertThat(lotto.getLotto()).hasSize(Lotto.LOTTO_SIZE),
                () -> assertThat(lotto.getLotto()).allMatch(
                        number -> number >= Lotto.START_INCLUSIVE_NUMBER && number < Lotto.END_EXCLUSIVE_NUMBER)
        );
    }
}
