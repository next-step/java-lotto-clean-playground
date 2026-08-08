package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottosTest {
    @Test
    @DisplayName("수동 로또를 포함하여 구매 개수만큼 로또를 생성한다")
    void createsManualAndAutoLottos() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(12000);

        int manualLottoCount = 3;
        int autoLottoCount = purchaseAmount.calculateAutoLottoCount(manualLottoCount);
        List<List<Integer>> manualLottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12),
                List.of(13, 14, 15, 16, 17, 18)
        );

        Lottos lottos = new Lottos(manualLottoNumbers, autoLottoCount);

        assertEquals(12, lottos.getLottos().size());
        assertContainsManualLottos(lottos, manualLottoNumbers);
    }

    private void assertContainsManualLottos(Lottos lottos, List<List<Integer>> manualLottoNumbers) {
        List<List<LottoNumber>> expectedManualLottos = manualLottoNumbers.stream()
                .map(Lotto::new)
                .map(Lotto::getNumbers)
                .toList();

        assertThat(lottos.getLottos())
                .extracting(Lotto::getNumbers)
                .containsAll(expectedManualLottos);
    }

    @Test
    @DisplayName("수동 로또가 없으면 자동 구매 개수만큼 로또를 생성한다")
    void createsOnlyAutoLottosWhenThereAreNoManualLottos() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14000);

        int manualLottoCount = 0;
        int autoLottoCount = purchaseAmount.calculateAutoLottoCount(manualLottoCount);
        List<List<Integer>> manualLottoNumbers = List.of();

        Lottos lottos = new Lottos(manualLottoNumbers, autoLottoCount);

        assertEquals(14, lottos.getLottos().size());
    }
}
