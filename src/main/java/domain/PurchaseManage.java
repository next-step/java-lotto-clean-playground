package domain;

import java.util.ArrayList;
import java.util.List;

public class PurchaseManage {
    private static final int LOTTO_PRICE = 1000;

    public Lottos buyLottos(int price, List<LottoNumber> manualLottos) {
        int totalCount = price / LOTTO_PRICE;
        int automaticLottoCount = totalCount - manualLottos.size();

        List<LottoNumber> purchaseLottos = new ArrayList<>(manualLottos);

        for (int i = 0; i < automaticLottoCount; i++) {
            purchaseLottos.add(new LottoNumber());
        }
        return new Lottos(purchaseLottos);
    }
}
