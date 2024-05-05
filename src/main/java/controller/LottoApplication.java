package controller;

import model.Consumer;
import model.Lotto;
import model.LottoStatistics;
import view.InputView;
import view.OutView;


public class LottoApplication {

    private Consumer consumer;
    private LottoStatistics lottoStatistics;

    public void InputMoneyAndInfoLottosApp() {

        consumer = new Consumer(InputView.inputMoney());
        consumer.BuyLottos();

        OutView.purchaseRecord(consumer.getHaveLottos());
    }

    public void InputCollectNumberApp() {

        lottoStatistics = new LottoStatistics(InputView.inputCollectedNumber());

        consumer.analizeAllLottos(lottoStatistics.getCollectNumber());

        lottoStatistics.configureMatchedCount(consumer.getHaveLottos());
    }

    public void InfoStatisticsApp() {

        double rateToReturn = lottoStatistics.calculateRatetoReturn(consumer.getMoney());

        OutView.statisticInfo(
                lottoStatistics.getMatchedCount(),
                lottoStatistics.getPrizeMoney(),
                rateToReturn);
    }
}