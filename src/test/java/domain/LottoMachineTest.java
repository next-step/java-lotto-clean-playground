package domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    static class FixedLottoNumberGenerator
            implements LottoNumberGenerator {

        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 14})
    void issuesRequestedNumberOfTickets(int purchaseCount) {
        // 준비
        LottoNumberGenerator generator =
                new FixedLottoNumberGenerator();
        LottoMachine machine = new LottoMachine(generator);

        // 실행
        Lottos lottos = machine.purchase(purchaseCount);

        // 검증
        assertThat(lottos.size()).isEqualTo(purchaseCount);
        lottos.getLottos().forEach(lotto ->
                assertThat(lotto.getSortedNumbers())
                        .containsExactly(1, 2, 3, 4, 5, 6)
        );
    }

    @Test
    void generatesSixUniqueNumbersWithinRange() {
        // 준비
        LottoNumberGenerator generator =
                new RandomLottoNumberGenerator();

        // 실행
        List<Integer> numbers = generator.generate();

        // 검증
        assertThat(numbers)
                .hasSize(6)
                .doesNotHaveDuplicates();
        assertThat(numbers)
                .allMatch(number ->
                        number >= 1 && number <= 45
                );
    }

    @Test
    void keepsManualTicketAndGeneratesRemainingTickets() {
        // 준비
        Lotto manualLotto = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        LottoMachine machine = new LottoMachine(
                () -> List.of(1, 2, 3, 4, 5, 6));

        // 실행
        Lottos result = machine.purchase(3, List.of(manualLotto));

        // 검증
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.getLottos().get(0)).isSameAs(manualLotto);
        assertThat(result.getLottos().subList(1, 3)).allSatisfy(lotto ->
                assertThat(lotto.getSortedNumbers())
                        .containsExactly(1, 2, 3, 4, 5, 6));
    }
}
