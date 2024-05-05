package controller;

import domain.LottoGame;
import domain.Lottos;
import view.InputView;
import view.OutputView;

import static view.OutputView.printAmountMessage;

public class LottoController {
    public void startGame() {
        OutputView.printStartMessage();
        int lottoTotalPrice = InputView.readLottoPrice();
        LottoGame lottoGame = new LottoGame(lottoTotalPrice);
        OutputView.printAmountMessage(lottoGame.calculateLottoAmount());
        OutputView.printLottos(lottoGame.getLottos());
    }
}
