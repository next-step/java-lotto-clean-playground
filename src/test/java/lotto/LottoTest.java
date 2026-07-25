package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @Test
    @DisplayName("구입 금액을 로또 가격으로 나누어 로또 개수 확인")
    void calculateLottoCount() {
        int purchaseCount = Lotto.calculateLottoCount(14000);

        assertThat(purchaseCount).isEqualTo(14);
    }
}
