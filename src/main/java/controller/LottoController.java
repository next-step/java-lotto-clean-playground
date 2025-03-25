package controller;

import model.*;
import view.InputHandler;
import view.OutputView;

public class LottoController {

    private final NumbersGenerator numbersGenerator;
    private final InputHandler inputHandler;

    public LottoController(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
        this.inputHandler = new InputHandler();
    }

    public void run() {
        PurchaseAmount purchaseAmount = inputHandler.getPurchaseAmount();
        Lottos lottos = Lottos.createLottos(purchaseAmount, numbersGenerator);
        OutputView.printLottos(lottos);

        WinningNumbers winningNumbers = inputHandler.getWinningNumbers();

        getResults(lottos, winningNumbers, purchaseAmount);
    }

    private static void getResults(Lottos lottos, WinningNumbers winningNumbers, PurchaseAmount purchaseAmount) {
        DrawResults drawResults = new DrawResults();
        drawResults.calculateResults(lottos, winningNumbers);
        OutputView.printDrawResults(drawResults);
        OutputView.printProfit(drawResults.calculateProfit(purchaseAmount));
    }
}
