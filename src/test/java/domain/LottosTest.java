package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottosTest {

    Lottos lottoRepository = new Lottos();

    @Test
    @DisplayName("로또들이 정상적으로 저장되어야한다")
    void 로또들이_정상적으로_저장되어야한다() {
        //given
        Lotto lotto1 = Lotto.from(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        Lotto lotto2 = Lotto.from(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12)));
        lottoRepository.addLotto(lotto1);
        lottoRepository.addLotto(lotto2);

        //when
        List<Lotto> lottoList = lottoRepository.getLottos();

        //then
        assertThat(lottoList.get(0)).isEqualTo(lotto1);
        assertThat(lottoList.get(1)).isEqualTo(lotto2);
        assertThat(lottoList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("외부에서 리스트 수정 시 예외 발생")
    void 외부에서_리스트_수정_시_예외_발생() {
        Lotto lotto = Lotto.from(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        lottoRepository.addLotto(lotto);

        List<Lotto> list = lottoRepository.getLottos();

        assertThatThrownBy(() -> list.add(Lotto.from(List.of(7, 8, 9, 10, 11, 12))))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
