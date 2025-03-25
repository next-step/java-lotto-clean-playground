package view;

import model.PurchaseAmount;
import model.WinningNumbers;
import utils.Utils;

import java.util.List;

public class InputHandler {

    public PurchaseAmount getPurchaseAmount() {
        OutputView.printPurchaseMessage();
        try {
            return PurchaseAmount.create(InputView.getInt());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getPurchaseAmount();
        }
    }

    public WinningNumbers getWinningNumbers() {
        OutputView.printLastWeekLottoInputMessage();
        String lastWeekLottoString = InputView.getString();
        try {
            return WinningNumbers.create(getLottoNumbers(lastWeekLottoString));
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
