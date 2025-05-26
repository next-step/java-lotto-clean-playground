package controller;

import domain.LottoList;
import view.InputView;
import view.OutputView;

public class PurchaseLotto {
    public void run() {
        //입력
        int money = InputView.getPurchaseAmount();
        int count = money / 1000;
        OutputView.printLottoAmount(count);

        //로또 생성
        LottoList lottoList = LottoList.generateLottoList(count);

        //출력
        OutputView.printLottoLists(lottoList);
    }
}
