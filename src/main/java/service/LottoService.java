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

    public Lottos generateLottos(List<Lotto> manualLottos, int totalLottoCount) {
        Lottos lottos = Lottos.from(manualLottos);
        while (lottos.getSize() < totalLottoCount) {
            final Lotto lotto = generateLotto();
            lottos.addLotto(lotto);
        }
        return lottos;
    }

    private Lotto generateLotto() {
        final List<Integer> numbers = lottoNumberGenerator.generate();
        return Lotto.from(numbers);
    }

    public LottoResult getLottoResult(Lottos lottos, Lotto winningLotto, BonusBall bonusBall) {
        final List<Score> scores = lottos.getScores(winningLotto, bonusBall);
        return new LottoResult(scores);
    }

}
