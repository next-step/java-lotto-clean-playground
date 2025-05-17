package application;

import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoPurchase;
import java.util.List;
import view.LottoView;

public class LottoApplication {
    public static void main(String[] args) {
        LottoView view = new LottoView();
        int purchaseAmount = view.readPurchaseAmount();

        LottoPurchase purchase = new LottoPurchase(purchaseAmount);
        int lottoCount = purchase.getLottoCount();
        view.printLottoCount(lottoCount);

        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.generate(lottoCount);

        view.printLotto(lottos);
    }
}
