import domain.LottoShop;
import domain.WinnigNumbersChecker;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final int inputMoney = InputView.inputMoney();

        LottoShop lottoShop = new LottoShop(inputMoney);
        OutputView.printLottos(lottoShop.saveLottos());

        final String inputWinningNumbers = InputView.inputWinningNumbers();

        WinnigNumbersChecker winnigNumbersChecker = new WinnigNumbersChecker();
        Map<Integer,Integer> winningResultMap = winnigNumbersChecker.analizeResultToMap();
        OutputView.printWinningResult(winningResultMap);
    }
}
