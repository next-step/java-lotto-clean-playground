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

    public int getLottoCount() {
        return purchaseAmount.getAmount() / LOTTO_PRICE;
    }

    public PurchasedLottos purchase(List<List<Integer>> manualLottoNumbers) {
        List<Lotto> manualLottos = createManualLottos(manualLottoNumbers);
        int manualLottoCount = manualLottos.size();
        int autoLottoCount = getAutoLottoCount(manualLottoCount);
        List<Lotto> autoLottos = issueAutoLottos(autoLottoCount);

        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLottos);
        lottos.addAll(autoLottos);

        return new PurchasedLottos(lottos, manualLottoCount, autoLottoCount);
    }

    public void validateManualPurchaseCount(int manualLottoCount) {
        validateManualLottoCount(manualLottoCount, getLottoCount());
    }

    private List<Lotto> createManualLottos(List<List<Integer>> manualLottoNumbers) {
        return manualLottoNumbers.stream()
                .map(Lotto::from)
                .toList();
    }

    private int getAutoLottoCount(int manualLottoCount) {
        int totalLottoCount = getLottoCount();
        validateManualLottoCount(manualLottoCount, totalLottoCount);
        return totalLottoCount - manualLottoCount;
    }

    private void validateManualLottoCount(int manualLottoCount, int totalLottoCount) {
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
