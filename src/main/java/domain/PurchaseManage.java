package domain;

import java.util.ArrayList;
import java.util.List;

public class PurchaseManage {
    private static final int LOTTO_PRICE = 1000;

    public Lottos buyLottos(int price) {
        int count = price / LOTTO_PRICE;
        List<LottoNumber> purchaseLottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            purchaseLottos.add(new LottoNumber());
        }
        return new Lottos(purchaseLottos);
    }
}
