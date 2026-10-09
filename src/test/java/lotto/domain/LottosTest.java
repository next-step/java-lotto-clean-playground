package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("로또가 원하는 개수만큼 생성된다")
    void 로또가_원하는_개수만큼_생성된다() {
        List<Lotto> lottoList = Lottos.generate(6, new LottoNumberGenerator()).getLottoList();

        assertThat(lottoList).hasSize(6);
    }


    @DisplayName("자동+ 수동 구매가 잘 되는지")
    @Test
    void 수동_번호와_자동_생성_번호를_합쳐서_Lottos를_생성한다() {
        List<List<Integer>> manualNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12)
        );

        Lottos lottos = Lottos.generate(manualNumbers, 3, new LottoNumberGenerator());

        assertThat(lottos.size()).isEqualTo(5);
    }
}
