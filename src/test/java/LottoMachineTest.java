import domain.LottoMachine;
import domain.LottoNumberGenerator;
import domain.Lottos;
import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {

    private static final LottoNumberGenerator FIXED_GENERATOR =
            () -> List.of(1, 2, 3, 4, 5, 6);

    @Nested
    @DisplayName("로또 구매 테스트")
    class buy {

        @ParameterizedTest
        @ValueSource(ints = {1000, 5000, 14000})
        @DisplayName("구입 금액을 1000으로 나눈 수만큼 로또를 발급한다 테스트")
        void 금액을_1000으로_나눈_수만큼_발급(int money) {
            int expectedSize = money / 1000;
            LottoMachine lottoMachine = new LottoMachine(FIXED_GENERATOR);

            Lottos lottos = lottoMachine.buy(new Money(money));

            assertThat(lottos.size()).isEqualTo(expectedSize);
        }
    }

    @Nested
    @DisplayName("구입 금액 검증 테스트")
    class validate {

        @ParameterizedTest
        @ValueSource(ints = {333, 1500, 5500})
        @DisplayName("구입 금액이 1000원 단위가 아니면 예외 발생 테스트")
        void 금액이_단위가_아니면_예외_발생(int money) {
            String throwMessage = "구입금액은 1000원 단위여야 합니다.";
            LottoMachine lottoMachine = new LottoMachine(FIXED_GENERATOR);

            assertThatThrownBy(() -> lottoMachine.buy(new Money(money)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }
    }
}
