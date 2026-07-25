import domain.Lotto;
import domain.LottoMachine;
import domain.LottoNumber;
import domain.LottoNumberGenerator;
import domain.Lottos;
import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {

    private static final LottoNumberGenerator FIXED_GENERATOR = () -> List.of(
            LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
            LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6));

    @Nested
    @DisplayName("로또 구매 테스트")
    class buy {

        @ParameterizedTest
        @ValueSource(ints = {1000, 5000, 14000})
        @DisplayName("구입 금액을 1000으로 나눈 수만큼 로또를 발급한다 테스트")
        void 금액을_1000으로_나눈_수만큼_발급(int money) {
            int expectedSize = money / 1000;
            LottoMachine lottoMachine = new LottoMachine(FIXED_GENERATOR);

            Lottos lottos = lottoMachine.buy(new Money(money), List.of());

            assertThat(lottos.size()).isEqualTo(expectedSize);
        }

        @Test
        @DisplayName("수동 로또를 그대로 포함하고 나머지는 자동으로 채워 발급한다 테스트")
        void 수동과_자동을_합쳐_발급() {
            Lotto manualLotto = Lotto.from(List.of("11", "12", "13", "14", "15", "16"));
            List<List<Integer>> expectedLottos = List.of(
                    List.of(11, 12, 13, 14, 15, 16),    // 수동
                    List.of(1, 2, 3, 4, 5, 6));         // 자동
            LottoMachine lottoMachine = new LottoMachine(FIXED_GENERATOR);

            Lottos lottos = lottoMachine.buy(new Money(2000), List.of(manualLotto));

            assertThat(lottos.getLottos())
                    .extracting(lotto -> lotto.getNumbers().stream().map(LottoNumber::getValue).toList())
                    .containsExactlyElementsOf(expectedLottos);
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

            assertThatThrownBy(() -> lottoMachine.buy(new Money(money), List.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }

        @Test
        @DisplayName("수동 구매 수가 전체 구매 수를 초과하면 예외 발생 테스트")
        void 수동_수가_전체_수를_초과하면_예외() {
            List<Lotto> manualLottos = List.of(
                    Lotto.from(List.of("1", "2", "3", "4", "5", "6")),
                    Lotto.from(List.of("7", "8", "9", "10", "11", "12")),
                    Lotto.from(List.of("13", "14", "15", "16", "17", "18")));
            String throwMessage = "수동 구매 수는 전체 구매 수를 초과할 수 없습니다.";
            LottoMachine lottoMachine = new LottoMachine(FIXED_GENERATOR);

            assertThatThrownBy(() -> lottoMachine.buy(new Money(1000), manualLottos))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(throwMessage);
        }
    }
}
