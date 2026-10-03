package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lottoGenerator.FixedLottoGenerator;
import lottoGenerator.RandomLottoGenerator;
import org.junit.jupiter.api.Test;
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
    }

    @Test
    void issuesTicketsWithGeneratedNumbers() {
        // 준비
        LottoMachine machine = new LottoMachine(new FixedLottoGenerator(List.of(1, 2, 3, 4, 5, 6)));
        // 실행
        List<Lotto> lottos = machine.purchase(3);
        // 검증
        assertThat(lottos).hasSize(3)
                .extracting(this::toNumbers)
                .containsOnly(List.of(1, 2, 3, 4, 5, 6));
    }

    private List<Integer> toNumbers(Lotto lotto) {
        return lotto.getNumbers().stream()
                .map(LottoNumber::getLottoNumber)
                .toList();
    }

}
