package domain.result;

import domain.lotto.PurchasedLottos;
import domain.lotto.WinningLotto;

public class LottoStatisticsCalculator {
    public LottoStatistics calculate(PurchasedLottos purchasedLottos, WinningLotto winningLotto) {
        LottoStatistics lottoStatistics = LottoStatistics.empty();
        purchasedLottos.values()
                .stream()
                .map(winningLotto::match)
                .forEach(lottoStatistics::record);
        return lottoStatistics;
    }
}
