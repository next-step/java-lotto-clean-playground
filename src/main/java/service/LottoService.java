package service;

import domain.LottoGroup;
import domain.LottoStore;
import dto.PurchaseLottosResponse;

public class LottoService {

    private final LottoStore lottoStore;

    public LottoService(LottoStore lottoStore) {
        this.lottoStore = lottoStore;
    }

    public PurchaseLottosResponse purchaseLottos(long amount){
        long lottoCount = lottoStore.getLottoCount(amount);
        LottoGroup lottoGroup = lottoStore.buyLottos(lottoCount);

        return new PurchaseLottosResponse(lottoCount, lottoGroup);
    }
}
