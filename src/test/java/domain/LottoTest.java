package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 번호를 문자열로 출력한다")
    void convertLottoNumbersToString() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        String result = lotto.toString();

        assertThat(result).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}
