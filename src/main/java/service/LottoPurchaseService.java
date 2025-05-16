package service;

import domain.Lotto;
import domain.Lottos;
import dto.LottoPurchaseRequest;

public class LottoPurchaseService {

    private final LottoGenerator lottoGenerator;

    public LottoPurchaseService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lottos purchase(LottoPurchaseRequest request) {
        int totalCount = request.purchaseAmount() / Lotto.PRICE;
        int autoCount = totalCount - request.manualCount();
        Lottos autoLottos = lottoGenerator.generateByCount(autoCount);
        return Lottos.merge(request.manualLottos(), autoLottos);
    }
}
