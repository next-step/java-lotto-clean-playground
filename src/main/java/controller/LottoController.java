package controller;

import model.*;
import model.lotto.Lotto;
import model.lotto.ManualLotto;
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
        PurchasePrice purchasePrice = getPurchasePrice();
        PurchaseAmount purchaseAmount = getPurchaseAmount(purchasePrice);

        List<ManualLotto> inputManualLottos = getManualLottos(purchaseAmount);
        Lottos lottos = Lottos.of(inputManualLottos, purchaseAmount.getAutoPurchaseAmount(), numbersGenerator);
        OutputView.printLottos(lottos, purchaseAmount);

        Lotto winningNumbers = getWinningNumbers();
        BonusBall bonusBall = getBonusBall(winningNumbers);
        calculateResults(lottos, winningNumbers, bonusBall);
    }

    private static void calculateResults(Lottos lottos, Lotto winningNumbers, BonusBall bonusBall) {
        DrawResults drawResults = new DrawResults();
        drawResults.calculateResults(lottos, winningNumbers, bonusBall);
        OutputView.printDrawResults(drawResults);
        OutputView.printProfit(drawResults.calculateProfit(lottos.size()));
    }

    private static PurchasePrice getPurchasePrice() {
        OutputView.printPurchaseMessage();
        long purchasePrice = InputView.getLong();
        try {
            return PurchasePrice.from(purchasePrice);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getPurchasePrice();
        }
    }

    private static PurchaseAmount getPurchaseAmount(PurchasePrice purchasePrice) {
        OutputView.printManualPurchaseAmountInputMessage();
        int manualPurchaseAmount = InputView.getInt();
        try {
            return PurchaseAmount.of(purchasePrice.getPurchasePrice(), manualPurchaseAmount);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getPurchaseAmount(purchasePrice);
        }
    }

    private static Lotto getWinningNumbers() {
        OutputView.printLastWeekLottoInputMessage();
        String lastWeekLottoString = InputView.getString();
        try {
            return ManualLotto.of(parseLottoNumbers(lastWeekLottoString));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }

    private static BonusBall getBonusBall(Lotto winningNumbers) {
        OutputView.printBonusBallInputMessage();
        int bonusNumber = InputView.getInt();
        try {
            return BonusBall.of(bonusNumber, winningNumbers);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getBonusBall(winningNumbers);
        }
    }

    private static List<ManualLotto> getManualLottos(PurchaseAmount purchaseAmount) {
        if (purchaseAmount.getManualPurchaseAmount() == 0) {
            return List.of();
        }
        OutputView.printManualLottosInputMessage();
        try {
            return getManualLottoList(purchaseAmount);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getManualLottos(purchaseAmount);
        }
    }

    private static List<ManualLotto> getManualLottoList(PurchaseAmount purchaseAmount) {
        List<ManualLotto> manualLottoList = new ArrayList<>();
        for (int i = 0; i < purchaseAmount.getManualPurchaseAmount(); i++) {
            List<Integer> manualLottoNumbers = parseLottoNumbers(InputView.getString());
            manualLottoList.add(ManualLotto.of(manualLottoNumbers));
        }
        return manualLottoList;
    }

    private static List<Integer> parseLottoNumbers(String lastWeekLottoString) {
        InputValidator.validateLottoNumbersInputPattern(lastWeekLottoString);
        return Utils.parseCommaSeparatedInts(lastWeekLottoString);
    }
}
