package domain.winning;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.lotto.LottoNumber;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusBallTest {
    @Test
    @DisplayName("bonus number duplicates winning numbers")
    void exceptionBonusDuplicated() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertThatThrownBy(() -> new BonusBall(lottoNumber, List.of(1, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
    }
}

