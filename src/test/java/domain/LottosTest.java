package domain;

import enums.LottoType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottosTest {

    @Test
    @DisplayName("lottos가_알맞은_수동로또수를_반환한다")
    void lottos가_알맞은_수동로또수를_반환한다() {
        //given
        Lotto lotto1 = Lotto.from(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)),LottoType.AUTO);
        Lotto lotto2 = Lotto.from(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12)),LottoType.MANUAL);

        //when
        Lottos lottos = new Lottos(List.of(lotto1, lotto2));

        //then
        assertThat(lottos.getAutoCount().getLottoCount()).isEqualTo(1);
        assertThat(lottos.getManualCount().getLottoCount()).isEqualTo(1);
        assertThat(lottos.getLottoCount().getLottoCount()).isEqualTo(2);
    }
}
