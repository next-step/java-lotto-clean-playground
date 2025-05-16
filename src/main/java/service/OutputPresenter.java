package service;

import domain.LottoStatistics;
import domain.Lottos;
import domain.Profit;

public interface OutputPresenter {
    void showPurchasedLottos(int manualCount, Lottos allLottos);
    void showStatistics(LottoStatistics statistics, Profit profit);
}

