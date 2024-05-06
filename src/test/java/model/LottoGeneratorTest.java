package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    private static final int lottoCount = 5;
    private static final int lottoPrice = 1000;
    private static final int totalPrice = lottoPrice * lottoCount;

    @Test
    @DisplayName("구입한 로또 금액을 입력하면 정해진 개수만큼의 자동 로또가 생성되어야 한다.")
    void createAutoLottosTest() {
        final List<Lotto> autoLottos = LottoGenerator.createAutoLottos(totalPrice);
        assertThat(autoLottos.size()).isEqualTo(lottoCount);
    }

    @Test
    @DisplayName("생성된 수동 로또와 자동 로또의 개수 합은 일정해야 한다.")
    void createManualLottosTest() {
        final List<Numbers> manualNumbers = List.of(
                new Numbers(List.of(1, 2, 3, 4, 5, 6)),
                new Numbers(List.of(21, 22, 23, 24, 25, 26)),
                new Numbers(List.of(31, 32, 33, 34, 35, 36))
        );
        final List<Lotto> allLottos = LottoGenerator.createManualLottos(totalPrice, manualNumbers);
        assertThat(allLottos.size()).isEqualTo(lottoCount);
    }

}
