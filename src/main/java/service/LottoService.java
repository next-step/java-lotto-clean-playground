package service;

import domain.Lotto;
import domain.LottoResult;
import domain.PurchasePrice;
import domain.LottoNumberGenerator;
import domain.Lottos;
import domain.Score;
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
        if (Lotto.PRICE == 0) {
            return purchasePrice.price();
        }
        return purchasePrice.price() / Lotto.PRICE;
    }

    public LottoResult getLottoResult(Lotto winningLotto, Lottos lottos) {
        final List<Integer> winningNumbers = winningLotto.numbers();
        final List<Score> scores = lottos.getScores(winningNumbers);
        return new LottoResult(scores);
    }

}
