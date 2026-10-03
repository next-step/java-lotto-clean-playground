package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMachineTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 14})
    void issuesRequestedNumberOfTickets(int purchaseCount) {
        // 준비
        LottoMachine machine = new LottoMachine();
        // 실행
        List<Lotto> lottos = machine.purchase(purchaseCount);
        // 검증
        assertThat(lottos).hasSize(purchaseCount);
    }

    private List<Integer> toNumbers(Lotto lotto) {
        return lotto.getNumbers().stream()
                .map(LottoNumber::getLottoNumber)
                .toList();
    }

    @Test
    void issuesManualAndAutoTickets() {
        // 준비
        LottoMachine machine = new LottoMachine();

        // 실행:
        machine.manualSelection(List.of(1, 2, 3, 4, 5, 6));
        machine.manualSelection(List.of(7, 8, 9, 10, 11, 12));
        List<Lotto> lottos = machine.purchase(2);

        // 검증:
        assertThat(lottos).hasSize(4)
                .extracting(this::toNumbers)
                .contains(
                        List.of(7, 8, 9, 10, 11, 12), // 수동
                        List.of(1, 2, 3, 4, 5, 6)     // 자동
                );
    }
}
