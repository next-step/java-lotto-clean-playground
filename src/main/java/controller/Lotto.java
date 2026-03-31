package controller;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;

public class Lotto {
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();
    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();
        resultView.printAllLottos(purchaseAmount);

        ArrayList<Integer> winnigNumbers = inputView.getWinningNumbers();
        resultView.printWinningLottoStatistics(purchaseAmount, winnigNumbers);
    }
}
