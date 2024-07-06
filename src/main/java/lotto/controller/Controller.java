package lotto.controller;

import lotto.domain.CheckPlace;
import lotto.domain.Reward;
import lotto.domain.WinNums;
import lotto.generator.NumberGenerator;
import lotto.generator.RandomNumberGenerator;
import lotto.domain.LottoGame;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    private final NumberGenerator numberGenerator = new RandomNumberGenerator();

    public void run() {
        int inputMoney = InputView.getMoney();
        LottoGame lottoGame = new LottoGame(inputMoney, numberGenerator);
        OutputView.printLottoes(lottoGame.getLottoList());
        WinNums winNums = new WinNums(InputView.getWinLotto(), InputView.getBonusBall());
        CheckPlace checkPlace = new CheckPlace(lottoGame, winNums);
        OutputView.printStatistics(checkPlace.getResultMap());
        Reward reward = new Reward(checkPlace.getResult(), inputMoney);
        OutputView.printReward(reward.getRate());
    }
}
