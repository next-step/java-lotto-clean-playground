import domain.Lotto;
import domain.LottoShop;
import domain.Lottos;
import domain.WinnigNumbersChecker;
import utils.WinningNumberFomatter;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
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
        Map<Integer,Integer> winningResultMap = winnigNumbersChecker.analizeResultToMap();
        OutputView.printWinningResult(winningResultMap);
    }
}
