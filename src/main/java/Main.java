import domain.Input;
import domain.GenerateLotto;
import domain.WinningLotto;
import exception.Lotto;
import exception.Money;
import view.Print;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GenerateLotto lotto = new GenerateLotto();
        WinningLotto winningLotto = new WinningLotto();
        Print print = new Print();
        Input input = new Input();

        int purchaseAmount = input.setPurchaseAmount();

        int manualCount = input.setManualCount();

        if (manualCount > 0) {
            lotto.getManualLotto(purchaseAmount, manualCount);
        }

        ArrayList<ArrayList<Integer>> totalLottos = lotto.getLotto(lotto.getRemainingMoney(purchaseAmount, manualCount));

        print.printPurchasedLottoCount(manualCount, totalLottos);

        ArrayList<Integer> winningNumbers = input.setWinningNumber();

        int bonusNumber = input.setBonusNumber(winningNumbers);

        winningLotto.totalCheckLotto(totalLottos, winningNumbers, bonusNumber);

        print.printWinningCount(winningLotto.winningCount, winningLotto.calculateRate(purchaseAmount));

    }
}