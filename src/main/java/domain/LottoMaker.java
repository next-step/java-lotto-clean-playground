package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMaker {

    private static final int LOTTO_PRICE = 1_000;
    private static int lottoQuantity;

    public List<Lotto> autoMake(int money) {
        lottoQuantity = money / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoQuantity; i++) {
            Lotto lotto = new Lotto();
            lottos.add(lotto);
        }

        return lottos;
    }

    public List<Lotto> manualMake(int money, List<List<Integer>> manulLottoNumbers) {
        lottoQuantity = money / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoQuantity; i++) {
            Lotto lotto = new Lotto(manulLottoNumbers.get(i));
            lottos.add(lotto);
        }

        return lottos;
    }
}
