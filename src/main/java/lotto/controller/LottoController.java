package lotto.controller;

import java.util.List;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run(){
        int money = InputView.inputMoney();
        int count = new Money(money).calculateNumberOfLottos();
        Lottos lottos = Lottos.generate(count, new LottoNumberGenerator());
        OutputView.printLottos(lottos);

        List<Integer> winningNumberInput = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber(); // 보너스 번호 입력 받기
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberInput,bonusNumber); // 보너스 번호 포함하여 WinningNumbers 생성
        WinningStatistics statistics = new WinningStatistics(lottos, winningNumbers);
        OutputView.printWinningStatistics(statistics);
    }
}
