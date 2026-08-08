package domain;

import view.InputView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PurchaseManage{
    private final PurchaseAmount purchaseAmount;

    LottoParser lottoParser = new LottoParser();
    public PurchaseManage(PurchaseAmount purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }
    public Lottos buyLottos(int manualCount) {
        int automaticLottoCount = purchaseAmount.calculateAutomaticCount();
        List<Lotto> manualLottos = makeManualLotto(manualCount);

        List<Lotto> purchaseLottos = new ArrayList<>(manualLottos);

        for (int i = 0; i < automaticLottoCount; i++) {
            purchaseLottos.add(new Lotto());
        }
        return new Lottos(purchaseLottos);
    }

    public List<Lotto> makeManualLotto(int manualCount) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            String input = InputView.getManualPurchasedLottos();
            List<Integer> numbers = lottoParser.parseInput(input);
            manualLottos.add(new Lotto(numbers));
        }
        return manualLottos;
    }
}
