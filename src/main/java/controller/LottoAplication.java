package controller;

import model.Consumer;
import view.InputView;
import view.OutView;

public class LottoAplication {

    public static void main(String[] args) {

        int money = InputView.Input();

        Consumer consumer = new Consumer(money);
        consumer.BuyLottos();

        OutView.PurchaseRecord(consumer.getHaveLottos());
    }
}
