import domain.Lotto;
import domain.LottoShop;
import domain.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoShopTest {
    @SuppressWarnings("NonAsciiCharacters")

    @DisplayName("자동_로또_수를_계산한다")
    @Test
    void 자동_로또_수를_계산한다() {
        // given
        LottoShop lottoShop = new LottoShop();
        int inputMoney = 5000;
        int manualCount = 2;

        // when
        int autoLottoCount = lottoShop.countAutoLottoTickets(inputMoney, manualCount);

        // then
        assertThat(autoLottoCount).isEqualTo(3);
    }

    @DisplayName("수동과_자동_로또를_합친다")
    @Test
    void 수동과_자동_로또를_합친다() {
        // given
        LottoShop lottoShop = new LottoShop();
        Lottos manualLottos = new Lottos(Arrays.asList(
                new Lotto(LottoFixture.DUMMY_NUMBER_1),
                new Lotto(LottoFixture.DUMMY_NUMBER_2)
        ));
        int autoLottoCount = 3; // 자동으로 3개의 로또 생성

        // when
        Lottos combinedLottos = lottoShop.saveLottos(autoLottoCount, manualLottos);

        //then
        //수동 로또만 확인
        assertThat(combinedLottos.getLottos())
                .extracting(Lotto::getLotto)
                .contains(
                        LottoFixture.DUMMY_NUMBER_1,
                        LottoFixture.DUMMY_NUMBER_2
                );

        //자동 로또는 개수만 확인
        assertThat(combinedLottos.getLottos().size() - 2).isEqualTo(autoLottoCount);
    }
}
