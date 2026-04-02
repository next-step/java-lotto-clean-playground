package controller;
import domain.Lotto;
import domain.Lottos;
import view.InputView;
import view.ResultView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoController {
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();
    Lotto lotto = new Lotto();
    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();
        Lottos lottos = new Lottos(purchaseAmount);
        List<Lotto> allLottos = lottos.getLottos();
        resultView.printAllLottos(allLottos);

        List<Integer> winningNumbers = inputView.getWinningNumbers();

        if(validateWinningNumbers(winningNumbers)) {
            resultView.printWinningLottoStatistics(purchaseAmount, winningNumbers, allLottos);
        }
    }

    public boolean validateWinningNumbers (List<Integer> winningNumbers) {
        if(winningNumbers.size() != 6) {
            System.out.println("당첨 숫자는 6개여야 해요.");
            throw new IllegalArgumentException("당첨 숫자는 6개여야 해요.");
        }

        Set<Integer> set = new HashSet<Integer>(winningNumbers);

        if(set.size() != winningNumbers.size()) {
            System.out.println("당첨 숫자에는 중복된 값이 들어올 수 없어요.");
            throw new IllegalArgumentException("당첨 숫자에는 중복된 값이 들어올 수 없어요.");
        }

        return true;
    }
}
