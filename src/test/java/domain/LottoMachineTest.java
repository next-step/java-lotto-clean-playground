package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoMachineTest {

    LottoMachine lottoMachine;

    @BeforeEach
    void beforeEach() {
        List<List<Integer>> fixedNumbersList = List.of(
                new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)),
                new ArrayList<>(List.of(2, 3, 4, 5, 6, 7)),
                new ArrayList<>(List.of(3, 4, 5, 6, 7, 8)),
                new ArrayList<>(List.of(4, 5, 6, 7, 8, 9)),
                new ArrayList<>(List.of(5, 6, 7, 8, 9, 10)),
                new ArrayList<>(List.of(7, 8, 9, 10, 11, 12))
        );
        lottoMachine = new LottoMachine(new FakeNumberGenerator(fixedNumbersList));
    }

    @Test
    @DisplayName("값에_맞는_로또수를_생성해야한다")
    void 값에_맞는_로또수를_생성해야한다() {

        //given
        List<Lotto> lottos = lottoMachine.generateLottos(LottoCount.from(3));

        //then
        assertThat(lottos.size()).isEqualTo(3);
    }
}
