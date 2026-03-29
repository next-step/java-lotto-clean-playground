package controller;

import view.OutputView;
import view.InputView;

public class Controller {
    public void run(){
        OutputView.printInputPurchaseAmount();
        int purchaseNumber = InputView.inputPurchaseMoney();
    //    System.out.println(purchaseNumber);
    }
}
