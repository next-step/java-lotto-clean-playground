package controller;

import domain.LottoGame;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void startGame(){
        OutputView.printStartMessage();
        int lottoTotalPrice = InputView.readLottoPrice();
        LottoGame lottoGame = new LottoGame(lottoTotalPrice);

    }


}
