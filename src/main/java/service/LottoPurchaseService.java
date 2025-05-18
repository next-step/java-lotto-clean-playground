package service;

import domain.Lotto;
import domain.Lottos;
import dto.LottoPurchaseDto;

public class LottoPurchaseService {

    private final LottoGenerator lottoGenerator;

    public LottoPurchaseService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lottos purchase(LottoPurchaseDto request) {
        int totalCount = request.purchaseAmount() / Lotto.PRICE;
        int manualCount = request.manualCount();
        int autoCount = totalCount - manualCount;

        Lottos generatedLottos = lottoGenerator.generate(request.manualLottos().getLottos(), autoCount);

        return generatedLottos;
    }
}
