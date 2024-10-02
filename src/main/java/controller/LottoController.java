package controller;

import java.util.List;
import domain.FindWinningLotto;
import dto.LottoDTO;
import view.InputView;
import view.OutputView;

import static domain.Lotto.multipleLottoGenerator;

public class LottoController {
    public static void main(String[] args) {
        int totalInvestment = InputView.inputToBuyLotto(); // 구입금액을 입력해주세요. (입력)
        int numberOfManualLottos = InputView.inputToBuyManualLotto(); // 수동으로 구매할 로또 수를 입력해주세요. (입력)
        int numberOfAutoLottos = totalInvestment / 1000 - numberOfManualLottos;

        List<List<Integer>> manualLottoInputs = InputView.inputToManualLotto(numberOfManualLottos); // 수동으로 구매할 번호를 입력해 주세요.

        OutputView.printResultOfBuying(numberOfManualLottos, numberOfAutoLottos); // 수동으로 장, 자동으로 개를 구매했습니다.

        List<List<Integer>> lottoCollection = multipleLottoGenerator(numberOfAutoLottos);
        manualLottoInputs.addAll(lottoCollection);
        OutputView.printResultOfAutoLotto(manualLottoInputs); // 로또 리스트 출력

        List<Integer> lastWeekWinningNumbers = InputView.lastWeekWinningNumber(); // 지난주 당첨 번호를 입력해주세요. (입력)
        int bonusWinningNumber = InputView.bonusWinningNumber(); // 보너스 볼을 입력해주세요. (입력)

        FindWinningLotto findWinningLotto = new FindWinningLotto();
        LottoDTO lottoDTO = findWinningLotto.countCollectNumber(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber, totalInvestment);

        OutputView.printLottoWinningStatistics(lottoDTO); // 당첨 통게 (출력)
    }
}
