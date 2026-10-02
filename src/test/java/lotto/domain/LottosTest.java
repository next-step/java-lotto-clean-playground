package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("수동으로 입력한 번호로 로또를 생성한다")
    void 수동으로_입력한_번호로_로또를_생성한다() {
        List<List<Integer>> inputLottos = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12)
        );

        Lottos lottos = Lottos.manualGenerate(inputLottos);

        assertThat(lottos.getLottoList()).hasSize(2);
        assertThat(lottos.getLottoList().get(0).getValues()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(lottos.getLottoList().get(1).getValues()).containsExactly(7, 8, 9, 10, 11, 12);
    }

    @Test
    @DisplayName("자동으로 요청한 개수만큼 로또를 생성한다")
    void 자동으로_요청한_개수만큼_로또를_생성한다() {
        Lottos lottos = Lottos.autoGenerate(
                new LottoCount(3), new LottoNumberGenerator());

        assertThat(lottos.getLottoList()).hasSize(3);

        for (Lotto lotto : lottos.getLottoList()) {
            assertThat(lotto.getValues()).hasSize(6);
            assertThat(lotto.getValues()).doesNotHaveDuplicates();
        }
    }

    @Test
    @DisplayName("수동 로또와 자동 로또를 정확히 결합한다")
    void 수동_로또와_자동_로또를_정확히_결합한다() {
        Lottos manualLottos = Lottos.manualGenerate(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12)
        ));
        Lottos autoLottos = Lottos.autoGenerate(
                new LottoCount(1), new LottoNumberGenerator());

        Lottos combinedLottos = manualLottos.combine(autoLottos);

        assertThat(combinedLottos.getLottoList()).hasSize(3);
        assertThat(combinedLottos.getLottoList().get(0).getValues()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(combinedLottos.getLottoList().get(1).getValues()).containsExactly(7, 8, 9, 10, 11, 12);
    }

}
