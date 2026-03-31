package controller;
import view.InputView;
import view.ResultView;

public class Lotto {
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();
    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();
        resultView.printAllLottos(purchaseAmount);
    }
}
