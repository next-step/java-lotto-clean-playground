package service;

import domain.Price;
import domain.LottoGenerator;
import domain.Lottos;

public class LottoService {

    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public Lottos getLottos(Price price) {
        return lottoGenerator.generateLottos(price);
    }

}
