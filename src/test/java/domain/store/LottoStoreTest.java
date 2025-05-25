package domain.store;

import static org.assertj.core.api.Assertions.assertThat;
import static support.LottoTestHelper.numbers;

import domain.lotto.Lotto;
import domain.lotto.Lottos;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.FixNumberGenerator;
import strategy.LottoNumberGenerator;

class LottoStoreTest {

    @Test
    @DisplayName("올바른 수의 자동 로또가 생성된다.")
    void shouldReturn_whenCorrectNumberOfLottos() {
        // given
        LottoNumberGenerator generator = new FixNumberGenerator();
        LottoStore store = new LottoStore(generator);

        // when
        Lottos lottos = store.buyAuto(3);

        // then
        assertThat(lottos.getValues())
                .hasSize(3)
                .allSatisfy(lotto -> assertThat(lotto.getNumbers())
                        .containsExactlyElementsOf(numbers(1, 2, 3, 4, 5, 6)));
    }
    
    @Test
    @DisplayName("수동 로또 입력 리스트로 수동 로또를 생성할 수 있다.")
    void shouldCreateManualLottos_fromValidInputs() {
        // given
        LottoStore store = new LottoStore(new FixNumberGenerator());
        List<String> inputs = List.of(
                "1,2,3,4,5,6",
                "7,8,9,10,11,12"
        );

        // when
        Lottos lottos = store.buyManual(inputs);

        // then
        assertThat(lottos.getValues())
                .hasSize(2)
                .extracting(Lotto::getNumbers)
                .containsExactly(
                        numbers(1, 2, 3, 4, 5, 6),
                        numbers(7, 8, 9, 10, 11, 12)
                );
    }
}
