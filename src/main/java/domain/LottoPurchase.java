package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;

    private final PurchaseAmount purchaseAmount;
    private final LottoGenerator lottoGenerator;

    public LottoPurchase(PurchaseAmount purchaseAmount, LottoGenerator lottoGenerator) {
        this.purchaseAmount = purchaseAmount;
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> issueRemainingAutoLottos(int manualLottoCount) {
        int autoLottoCount = getAutoLottoCount(manualLottoCount);
        return issueAutoLottos(autoLottoCount);
    }

    public int getLottoCount() {
        return purchaseAmount.getAmount() / LOTTO_PRICE;
    }

    private int getAutoLottoCount(int manualLottoCount) {
        int totalLottoCount = getLottoCount();
        validateManualLottoCount(manualLottoCount, totalLottoCount);
        return totalLottoCount - manualLottoCount;
    }

    private void validateManualLottoCount(int manualLottoCount, int totalLottoCount) {
        if (manualLottoCount < 0) {
            throw new IllegalArgumentException("수동 구매 수는 음수일 수 없습니다.");
        }

        if (manualLottoCount > totalLottoCount) {
            throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 초과할 수 없습니다.");
        }
    }

    private List<Lotto> issueAutoLottos(int autoLottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(lottoGenerator.generate());
        }

        return lottos;
    }
}
