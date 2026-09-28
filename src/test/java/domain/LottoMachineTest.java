package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lottoGenerator.RandomLottoGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMachineTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 14})
    void issuesRequestedNumberOfTickets(int purchaseCount) {
        // 준비
        LottoMachine machine = new LottoMachine(new RandomLottoGenerator());
        // 실행
        List<Lotto> lottos = machine.purchase(purchaseCount);
        // 검증
        assertThat(lottos).hasSize(purchaseCount);
        lottos.forEach(lotto -> assertValidNumbers(lotto.getNumbers()));
    }

    private void assertValidNumbers(List<Integer> numbers) {
        assertThat(numbers).hasSize(6).doesNotHaveDuplicates().isSorted();
        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
    }


}
