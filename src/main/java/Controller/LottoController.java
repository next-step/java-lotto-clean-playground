package Controller;

import Creator.RandomNumberCreator;
import Model.*;
import View.InputData;
import View.InputView;
import View.ResultView;

import java.util.ArrayList;

import static Model.Constants.LOTTO_PRICE;
import static View.InputData.*;
import static View.InputView.inputFirstLotto;

public class LottoController {
    public static void main(String[] args) {
        Lottos lottos = new Lottos();

        inputMoney = InputView.getInput();
        handLotto = InputView.getHandLotto(lottos);
        autoLotto = InputView.getAutoLotto(lottos,getAutoLottoCount(inputMoney, handLotto));
        ResultView.printLottoResult(handLotto,autoLotto,lottos);
        inputFirstLotto();
        ResultView.finalResult(lottos);
    }

    private static int getAutoLottoCount(int inputMoney, int handLotto) {
        return inputMoney / LOTTO_PRICE - handLotto;
    }
}
