package controller;

import model.*;
import utils.Utils;
import view.InputValidator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final NumbersGenerator numbersGenerator;

    public LottoController(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        Lottos lottos = Lottos.of(purchaseAmount, numbersGenerator);
        OutputView.printLottos(lottos);
        Lotto winningNumbers = getWinningNumbers();
        BonusBall bonusBall = getBonusBall(winningNumbers);
        getResults(lottos, winningNumbers, purchaseAmount, bonusBall);
    }

    private static void getResults(Lottos lottos, Lotto winningNumbers, PurchaseAmount purchaseAmount, BonusBall bonusBall) {
        DrawResults drawResults = new DrawResults();
        drawResults.calculateResults(lottos, winningNumbers, bonusBall);
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
            return Lotto.from(getLottoNumbers(lastWeekLottoString));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }

    private static List<Integer> getLottoNumbers(String lastWeekLottoString) {
        InputValidator.validateLottoNumbersInputPattern(lastWeekLottoString);
        return Utils.parseCommaSeparatedInts(lastWeekLottoString);
    }

    private static BonusBall getBonusBall(Lotto winningNumbers) {
        OutputView.printBonusBallInputMessage();
        try {
            return BonusBall.of(InputView.getInt(), winningNumbers);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getBonusBall(winningNumbers);
        }
    }
}
