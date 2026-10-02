package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("로또가 원하는 개수만큼 생성된다")
    void 로또가_원하는_개수만큼_생성된다() {
        List<Lotto> lottoList = Lottos.autoGenerate(6, new LottoNumberGenerator()).getLottoList();

        assertThat(lottoList).hasSize(6);
    }

}
