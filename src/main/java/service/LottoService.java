package service;

import domain.PurchasePrice;
import domain.LottoGenerator;
import domain.Lottos;

public class LottoService {

    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public Lottos getLottos(PurchasePrice purchasePrice) {
        return lottoGenerator.generateLottos(purchasePrice);
    }

}
