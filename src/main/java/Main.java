import domain.*;
import utils.WinningNumberFomatter;
import utils.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final int inputMoney = InputView.inputMoney();

        LottoShop lottoShop = new LottoShop(inputMoney);
        Lottos lottos = lottoShop.saveLottos();
        OutputView.printLottos(lottoShop.saveLottos());

        final String inputWinningNumbers = InputView.inputWinningNumbers();
        WinningNumberFomatter.formWinningNumbers(inputWinningNumbers);

        WinnigNumbersChecker winnigNumbersChecker = new WinnigNumbersChecker(lottos);
        List<Integer> winningResultList = winnigNumbersChecker.analizeResultToList();
        Map<Integer,Integer> winningResultMap = WinningResult.analizeResultToMap(winningResultList);
        OutputView.printWinningResult(winningResultMap);
    }
}
