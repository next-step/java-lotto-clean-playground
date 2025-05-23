package domain;

import domain.generator.LottoGenerator;
import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    private static final String MANUAL_LOTTO_COUNT_SMALLER_THAN_TOTAL = "수동 로또 수는 총 구매 로또 수(%d개)이하이어야 합니다.";
    private final LottoGenerator lottoGenerator;

    public LottoStore(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public void validateManualCount(PurchaseAmount purchaseAmount, int manualCount) {
        if (manualCount > purchaseAmount.getLottoCount()) {
            throw new IllegalArgumentException(String.format(
                    MANUAL_LOTTO_COUNT_SMALLER_THAN_TOTAL, purchaseAmount.getLottoCount()));
        }
    }

    public List<Lotto> buyLotto(PurchaseAmount purchaseAmount, List<Lotto> manualLottos) {
        int manualCount = manualLottos.size();
        int totalCount = purchaseAmount.getLottoCount();

        List<Lotto> result = new ArrayList<>(manualLottos);
        int autoCount = totalCount - manualCount;

        for (int i = 0; i<autoCount; i++) {
            result.add(lottoGenerator.generate());
        }
        return result;
    }
}
