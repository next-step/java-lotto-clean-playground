import domain.Lotto;
import domain.LottoShop;
import domain.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoShopTest {

    @DisplayName("자동_로또_수를_계산한다")
    @Test
    void 자동_로또_수를_계산한다() {
        LottoShop lottoShop = new LottoShop();
        int inputMoney = 5000;
        int manualCount = 2;

        int autoLottoCount = lottoShop.countAutoLottoTickets(inputMoney, manualCount);

        assertThat(autoLottoCount).isEqualTo(3);
    }

    @DisplayName("수동과_자동_로또를_합친다")
    @Test
    void 수동과_자동_로또를_합친다() {
        LottoShop lottoShop = new LottoShop();
        Lottos manualLottos = new Lottos(Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12))
        ));
        int autoLottoCount = 3; // 자동으로 3개의 로또 생성

        Lottos combinedLottos = lottoShop.saveLottos(autoLottoCount, manualLottos);

        assertThat(combinedLottos.getLottos())
                .extracting(Lotto::getLotto)
                .containsExactlyInAnyOrder(
                        Arrays.asList(1, 2, 3, 4, 5, 6),
                        Arrays.asList(7, 8, 9, 10, 11, 12)
                );
    }

}
