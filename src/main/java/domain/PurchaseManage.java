package domain;

import java.util.List;

public class PurchaseManage{
    private final PurchaseAmount purchaseAmount;

    public PurchaseManage(PurchaseAmount purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }
    public Lottos buyLottos(List<String> manualInputs) {
        int automaticLottoCount = purchaseAmount.calculateAutomaticCount();
        Lottos lottos = new Lottos();
        lottos.makeManualLottos(manualInputs);
        lottos.makeAutomaticLottos(automaticLottoCount);

        return lottos;
    }
}
