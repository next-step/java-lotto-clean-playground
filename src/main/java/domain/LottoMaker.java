package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMaker {

    private static final int LOTTO_PRICE = 1_000;
    private static int lottoQuantity;

    public List<Lotto> make(int money) {
        lottoQuantity = money / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoQuantity; i++) {
            Lotto lotto = new Lotto();
            lottos.add(lotto);
        }
        return lottos;
    }

    public int getLottoQuantity(){
        return lottoQuantity;
    }
}
