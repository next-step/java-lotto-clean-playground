package controller;

import model.*;
import utils.Utils;
import view.InputValidator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final NumbersGenerator numbersGenerator;
    private final InputHandler inputHandler;

    public LottoController(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
        this.inputHandler = new InputHandler();
    }

    public void run() {

        PurchaseAmount purchaseAmount = getPurchaseAmount();
        Lottos lottos = Lottos.createLottos(purchaseAmount, numbersGenerator);
        OutputView.printLottos(lottos);

        Lotto winningNumbers = getWinningNumbers();

        getResults(lottos, winningNumbers, purchaseAmount);
    }

    private static void getResults(Lottos lottos, Lotto winningNumbers, PurchaseAmount purchaseAmount) {
        DrawResults drawResults = new DrawResults();
        drawResults.calculateResults(lottos, winningNumbers);
        OutputView.printDrawResults(drawResults);
        OutputView.printProfit(drawResults.calculateProfit(purchaseAmount));
    }

    private static PurchaseAmount getPurchaseAmount() {
        OutputView.printPurchaseMessage();
        try {
            return PurchaseAmount.create(InputView.getInt());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private static Lotto getWinningNumbers() {
        OutputView.printLastWeekLottoInputMessage();
        String lastWeekLottoString = InputView.getString();
        try {
            return Lotto.create(getLottoNumbers(lastWeekLottoString));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }

    private static List<Integer> getLottoNumbers(String lastWeekLottoString) {
        InputValidator.validateLottoNumbersInputPattern(lastWeekLottoString);
        return Utils.parseCommaSeparatedInts(lastWeekLottoString);
    }
}
