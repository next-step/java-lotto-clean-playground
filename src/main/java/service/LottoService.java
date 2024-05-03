package service;

import java.util.List;
import model.Lotto;
import model.LottoGenerator;

public class LottoService {

    public List<Lotto> lottoCreate(final int price) {
        return LottoGenerator.generateLotto(price);
    }



}
