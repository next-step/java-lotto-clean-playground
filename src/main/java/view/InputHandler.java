package view;

import model.Lotto;
import model.PurchaseAmount;
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

    public Lotto getWinningNumbers() {
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
