package domain.store;

import static domain.store.LottoStore.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static support.LottoTestHelper.numbers;

import domain.lotto.Lottos;
import domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.FixNumberGenerator;
import strategy.LottoNumberGenerator;

class LottoStoreTest {

    @Test
    @DisplayName("구입 금액에 맞춰 올바른 수의 로또가 생성된다.")
    void shouldReturn_whenCorrectNumberOfLottos() {
        // given
        LottoNumberGenerator generator = new FixNumberGenerator();
        LottoStore store = new LottoStore(generator);
        Money purchaseAmount = Money.from("3000");

        // when
        Lottos lottos = store.buy(purchaseAmount);

        // then
        assertThat(lottos.getValues())
                .hasSize(3)
                .allSatisfy(lotto -> assertThat(lotto.getNumbers())
                        .containsExactlyElementsOf(numbers(1, 2, 3, 4, 5, 6)));
    }

    @DisplayName("구입 금액이 로또 판매 금액 미만이면 예외가 발생한다.")
    @Test
    void shouldThrowException_whenBelowMinimumAmount() {
        // given
        LottoStore store = new LottoStore(new FixNumberGenerator());
        Money invalidAmount = Money.from("999");

        // when & then
        assertThatThrownBy(() -> store.buy(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 %s원 이상 입력해야 합니다.".formatted(LOTTO_PRICE.amount()));
    }

    @DisplayName("구입 금액이 로또 판매 금액 단위가 아니면 예외가 발생한다.")
    @Test
    void shouldThrowException_whenInvalidUnit() {
        // given
        LottoStore store = new LottoStore(new FixNumberGenerator());
        Money invalidAmount = Money.from("1100");

        // when & then
        assertThatThrownBy(() -> store.buy(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 %s원 단위로 입력해야 합니다.".formatted(LOTTO_PRICE.amount()));
    }

}
