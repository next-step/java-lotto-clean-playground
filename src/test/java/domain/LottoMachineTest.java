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
        List<Lotto> lottos = machine.purchase(purchaseCount);

        // 검증
        assertThat(lottos).hasSize(purchaseCount);
        lottos.forEach(lotto ->
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
}
