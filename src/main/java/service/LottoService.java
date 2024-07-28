package service;

import domain.BonusBall;
import domain.Lotto;
import domain.LottoResult;
import domain.LottoNumberGenerator;
import domain.Lottos;
import domain.Score;
import java.util.List;

public class LottoService {

    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    public Lottos generateLottos(int lottoCount) {
        Lottos lottos = new Lottos();
        for (int count = 0; count < lottoCount; count++) {
            final List<Integer> numbers = lottoNumberGenerator.generate();
            final Lotto lotto = Lotto.from(numbers);
            lottos.addLotto(lotto);
        }
        return lottos;
    }

    public LottoResult getLottoResult(Lottos lottos, Lotto winningLotto, BonusBall bonusBall) {
        final List<Score> scores = lottos.getScores(winningLotto, bonusBall);
        return new LottoResult(scores);
    }

}
