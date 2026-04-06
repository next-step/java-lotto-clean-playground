package domain;

import generator.NumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {
    private final NumberGenerator numberGenerator;

    public LottoShop(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos purchase(PurchaseAmount purchaseAmount, Lottos manualLottos) {
        int autoLottoCount = purchaseAmount.calculateAutoLottoCount(manualLottos.size());
        List<Lotto> autoLottos = createAutoLottos(autoLottoCount);
        return new Lottos(mergeLottos(manualLottos.lottoToList(), autoLottos));
    }

    private List<Lotto> createAutoLottos(int autoLottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(new Lotto(numberGenerator.generate()));
        }
        return lottos;
    }

    private List<Lotto> mergeLottos(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        List<Lotto> mergedLottos = new ArrayList<>(manualLottos);
        mergedLottos.addAll(autoLottos);
        return mergedLottos;
    }
}
