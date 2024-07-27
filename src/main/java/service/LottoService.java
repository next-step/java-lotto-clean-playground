package service;

import domain.BonusBall;
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
            final Lotto lotto = Lotto.from(numbers);
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

    public LottoResult getLottoResult(Lottos lottos, Lotto winningLotto, BonusBall bonusBall) {
        final List<Score> scores = lottos.getScores(winningLotto, bonusBall);
        return new LottoResult(scores);
    }

}
