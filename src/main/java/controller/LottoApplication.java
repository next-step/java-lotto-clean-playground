package controller;

import model.Consumer;
import model.Lotto;
import model.LottoMachine;
import model.LottoStatistics;
import view.InputView;
import view.OutView;


public class LottoApplication {

    private Consumer consumer;
    private LottoStatistics lottoStatistics;

    private LottoMachine lottoMachine;

    public void InputMoneyAndDirectCountApp() {

        String inputMoney = InputView.inputMoney();
        String directCount = InputView.inputDirectCount();

        consumer = new Consumer(inputMoney, directCount);
    }

    public void InputDirectNumbersApp() {

        lottoMachine = new LottoMachine();
        lottoMachine.makeDirectNumber(InputView.inputDirectNumber(Consumer.getDirectCount()));
    }

    public void MakeAutoNumbersAndLottosInfoApp() {

        int autoCount = Consumer.getMoney() / Lotto.getPrice() - Consumer.getDirectCount();

        lottoMachine.makeAutoNumber(autoCount);
        consumer.BuyLottos(lottoMachine.getAutoNumbers(), lottoMachine.getDirectNumbers());

        OutView.purchaseRecord(consumer.getHaveLottos(), Consumer.getDirectCount(), autoCount);
    }


    public void InputCollectNumberApp() {

        lottoStatistics = new LottoStatistics(InputView.inputCollectedNumber());
        lottoStatistics.initBonusBall(InputView.inputBonusBallNumber());

        consumer.analizeAllLottos(lottoStatistics.getCollectNumber(), lottoStatistics.getBonusBall().value());

        lottoStatistics.configureMatchedCount(consumer.getHaveLottos());
    }

    public void InfoStatisticsApp() {

        double rateToReturn = lottoStatistics.calculateRatetoReturn(consumer.getMoney());

        OutView.statisticInfo(lottoStatistics.getRatingInfo(), rateToReturn);
    }
}
