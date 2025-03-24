package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottosTest {

    @Test
    void 구매한_개수만큼_로또_번호가_발급되어야_한다() {
        int amount = 5;
        Lottos lottos = Lottos.createLottos(amount, new LottoNumberGenerator());
        assertThat(lottos.size()).isEqualTo(5);
    }

    @Test
    void getLottos를_통해_얻은_객체를_변경하면_예외가_발생해야_한다() {
        int amount = 5;
        Lottos lottos = Lottos.createLottos(amount, new LottoNumberGenerator());
        List<Lotto> findLottos = lottos.getLottos();

        assertThatThrownBy(() -> findLottos.add(new Lotto(new LottoNumberGenerator())))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
