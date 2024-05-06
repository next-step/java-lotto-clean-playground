import domain.Input;
import domain.GenerateLotto;
import domain.WinningLotto;
import view.Print;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GenerateLotto lotto = new GenerateLotto();
        WinningLotto winningLotto = new WinningLotto();
        Print print = new Print();
        Input input = new Input();

        int purchaseAmount = input.setPurchaseAmount();

        ArrayList<ArrayList<Integer>> totalLottos = lotto.getLotto(purchaseAmount);

        print.printPurchasedLottoCount(totalLottos);

        ArrayList<Integer> winningNumbers = input.setWinningNumber();

        winningLotto.totalCheckLotto(totalLottos, winningNumbers);

        print.printWinningCount(winningLotto.winningCount, winningLotto.calculateRate(purchaseAmount));

    }
}