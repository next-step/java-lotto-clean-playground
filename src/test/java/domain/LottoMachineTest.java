package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @DisplayName("요청한 시도 횟수만큼 로또를 발행한다.")
    @Test
    void issueLottos() {
        // given
        LottoNumberGenerator stubGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        LottoMachine lottoMachine = new LottoMachine(stubGenerator);
        int trialCount = 5;
        // when
        List<Lotto> lottos = lottoMachine.issue(trialCount);
        // then
        assertThat(lottos).hasSize(trialCount);
        assertThat(lottos.get(0).getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
