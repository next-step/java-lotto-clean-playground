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

    @Test
    void issuesManualAndAutoTickets() {
        // 준비
        LottoMachine machine = new LottoMachine(new FixedLottoGenerator(List.of(1, 2, 3, 4, 5, 6)));
        List<Integer> manualNumbers = List.of(7, 8, 9, 10, 11, 12);

        // 실행: 수동 1장 등록 후 자동 2장 발권
        machine.manualSelection(manualNumbers);
        List<Lotto> lottos = machine.purchase(2);

        // 검증: 총 3장이어야 하고, 수동 번호와 자동 번호가 모두 들어있어야 함
        assertThat(lottos).hasSize(3)
                .extracting(this::toNumbers)
                .contains(
                        List.of(7, 8, 9, 10, 11, 12), // 수동
                        List.of(1, 2, 3, 4, 5, 6)     // 자동
                );
    }
}
