package controller;

import model.*;
import utils.Utils;
import view.InputValidator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final NumbersGenerator numbersGenerator;

    public LottoController(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();

        Lottos manualLottos = getManualLottos(purchaseAmount);
        Lottos autoLottos = Lottos.auto(purchaseAmount.getAutoPurchaseAmount(), numbersGenerator);
        OutputView.printLottos(manualLottos, autoLottos);

        Lotto winningNumbers = getWinningNumbers();
        BonusBall bonusBall = getBonusBall(winningNumbers);

        calculateResults(manualLottos, autoLottos, winningNumbers, bonusBall);
    }

    private static void calculateResults(Lottos manualLottos, Lottos autoLottos, Lotto winningNumbers, BonusBall bonusBall) {
        DrawResults drawResults = new DrawResults();
        drawResults.calculateResults(manualLottos, autoLottos, winningNumbers, bonusBall);
        OutputView.printDrawResults(drawResults);
        OutputView.printProfit(drawResults.calculateProfit(manualLottos.size() + autoLottos.size()));
    }

    private static PurchaseAmount getPurchaseAmount() {
        OutputView.printPurchaseMessage();
        int purchasePrice = InputView.getInt();
        OutputView.printManualPurchaseAmountInputMessage();
        int manualPurchaseAmount = InputView.getInt();
        try {
            return PurchaseAmount.of(purchasePrice, manualPurchaseAmount);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private static Lotto getWinningNumbers() {
        OutputView.printLastWeekLottoInputMessage();
        String lastWeekLottoString = InputView.getString();
        try {
            return Lotto.from(parseLottoNumbers(lastWeekLottoString));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
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


    private static Lottos getManualLottos(PurchaseAmount purchaseAmount) {
        if (purchaseAmount.getManualPurchaseAmount() == 0) {
            return Lottos.manual(List.of());
        }
        OutputView.printManualLottosInputMessage();
        try {
            return Lottos.manual(getManualLottoList(purchaseAmount));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getManualLottos(purchaseAmount);
        }
    }

    private static List<Lotto> getManualLottoList(PurchaseAmount purchaseAmount) {
        List<Lotto> manualLottoList = new ArrayList<>();
        for (int i = 0; i < purchaseAmount.getManualPurchaseAmount(); i++) {
            List<Integer> manualLottoNumbers = parseLottoNumbers(InputView.getString());
            manualLottoList.add(Lotto.from(manualLottoNumbers));
        }
        return manualLottoList;
    }

    private static List<Integer> parseLottoNumbers(String lastWeekLottoString) {
        InputValidator.validateLottoNumbersInputPattern(lastWeekLottoString);
        return Utils.parseCommaSeparatedInts(lastWeekLottoString);
    }
}
