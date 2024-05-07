package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {

    @Test
    void generateLottos() {
        int count = 5;
        List<Lotto> lottos = LottoMachine.generateLottos(count);
        assertThat(lottos.size()).isEqualTo(count);
        assertThat(lottos.stream().distinct().count()).isEqualTo(count);
    }
}
