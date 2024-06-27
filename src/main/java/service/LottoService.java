package service;

import domain.Lotto;
import domain.PurchasePrice;
import domain.LottoNumberGenerator;
import domain.Lottos;
import java.util.List;

public class LottoService {

    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    public Lottos generateLottos(PurchasePrice purchasePrice) {
        final int numberOfLotto = getNumberOfLotto(purchasePrice);

        Lottos lottos = new Lottos();
        for (int count = 0; count < numberOfLotto; count++) {
            final List<Integer> numbers = lottoNumberGenerator.generate();
            final Lotto lotto = new Lotto(numbers);
            lottos.addLotto(lotto);
        }
        return lottos;
    }

    private int getNumberOfLotto(PurchasePrice purchasePrice) {
        return purchasePrice.price() / Lotto.PRICE;
    }

}
