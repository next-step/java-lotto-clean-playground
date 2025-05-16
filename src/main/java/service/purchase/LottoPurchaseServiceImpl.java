package service.purchase;

import domain.Lotto;
import domain.Lottos;
import dto.LottoPurchaseDto;
import service.LottoGenerator;
import service.LottoPurchaseService;

public class LottoPurchaseServiceImpl implements LottoPurchaseService {

    private final LottoGenerator lottoGenerator;

    public LottoPurchaseServiceImpl(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    @Override
    public Lottos purchase(LottoPurchaseDto request) {
        int totalCount = request.purchaseAmount() / Lotto.PRICE;
        int autoCount = totalCount - request.manualCount();
        Lottos autoLottos = lottoGenerator.generateByCount(autoCount);
        return Lottos.merge(request.manualLottos(), autoLottos);
    }
}
