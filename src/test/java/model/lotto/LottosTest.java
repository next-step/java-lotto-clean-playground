package model.lotto;

import model.dice.Dice;
import model.dice.FakeDice;
import model.lotto.vo.LottoMarket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class LottosTest {

    private Dice dice;

    @BeforeEach
    void init() {
        dice = new FakeDice();
    }

    @Test
    void 천원_단위_로또_생성_정상() {
        // given
        String moneyInput = "14000";
        LottoMarket market = LottoMarket.from(moneyInput);
        int lottoSize = market.calculateBoughtLottoSize();

        // when & then
        assertDoesNotThrow(() -> Lottos.createWith(dice, lottoSize));
    }

    @Test
    void 생성된_로또_갯수_계산() {
        // given
        String moneyInput = "14000";
        LottoMarket market = LottoMarket.from(moneyInput);
        int lottoSize = market.calculateBoughtLottoSize();

        // when
        Lottos lottos = Lottos.createWith(dice, lottoSize);

        // then
        assertThat(lottos.getLottosSize()).isEqualTo(lottoSize);
    }

    @Test
    void 생성된_로또_목록_확인() {
        // given
        String moneyInput = "1000";
        LottoMarket market = LottoMarket.from(moneyInput);
        int lottoSize = market.calculateBoughtLottoSize();
        Lottos lottos = Lottos.createWith(dice, lottoSize);
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<List<Integer>> totalLottoNumbers = lottos.getTotalLottoNumbers();

        // then
        assertSoftly(softly -> {
            softly.assertThat(totalLottoNumbers.size()).isEqualTo(1);
            softly.assertThat(totalLottoNumbers.get(0)).isEqualTo(expectedNumbers);
        });
    }
}
