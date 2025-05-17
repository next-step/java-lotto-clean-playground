package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.FixNumberGenerator;
import strategy.LottoNumberGenerator;

class LottoStoreTest {

    @Test
    @DisplayName("구입 금액에 맞춰 올바른 수의 로또가 생성된다")
    void buy_shouldReturnCorrectNumberOfLottos() {
        // given
        LottoNumberGenerator generator = new FixNumberGenerator();
        LottoStore store = new LottoStore(generator);
        PurchaseAmount purchaseAmount = new PurchaseAmount(3000);

        // when
        Lottos lottos = store.buy(purchaseAmount);

        // then
        assertThat(lottos.getValues())
                .hasSize(3)
                .allSatisfy(lotto -> assertThat(lotto.getNumbers())
                        .containsExactly(new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(4),
                                new LottoNumber(5),
                                new LottoNumber(6))
                );
    }
}
