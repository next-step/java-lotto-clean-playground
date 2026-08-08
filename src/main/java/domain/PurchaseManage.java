package domain;

import view.InputView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PurchaseManage{
    private final PurchaseAmount purchaseAmount;

    public PurchaseManage(PurchaseAmount purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }
    public Lottos buyLottos(int manualCount, List<String> manualInputs) {
        int automaticLottoCount = purchaseAmount.calculateAutomaticCount();
        Lottos lottos = new Lottos();
        lottos.makeManualLottos(manualInputs);
        lottos.makeAutomaticLottos(automaticLottoCount);

        return lottos;
    }
}
