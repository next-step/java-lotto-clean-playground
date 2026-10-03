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
        // 1. 금액 입력 받기
        Lottos lottos = purchaseLottos();
        // 2. 당첨 번호 입력 받기
        checkWinning(lottos);
    }


    private Lottos purchaseLottos(){
        int moneyInput = InputView.inputMoney();
        Money money = new Money(moneyInput); // Money 객체 생성 (뒤에서 예외처리 적용하려고)
        int manualCount = InputView.inputManualCount();
        money.validateManualCount(manualCount); // 수동구매 개수가 총 구매 개수를 초과하는지 검증

        List<List<Integer>> manualNumbers = InputView.inputManualNumbers(manualCount); // 수동으로 구매할 번호 입력 받기
        int autoCount = money.calculateNumberOfLottos() - manualCount; // 자동으로 구매할 로또 개수 계산
        Lottos lottos = Lottos.generateManual(manualNumbers, autoCount, new LottoNumberGenerator()); // 수동 + 자동 로또 생성
        OutputView.printLottos(lottos, manualCount);

        return lottos;
    }

    private void checkWinning(Lottos lottos){
        List<Integer> winningNumberInput = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber(); // 보너스 번호 입력 받기
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberInput,bonusNumber); // 보너스 번호 포함하여 WinningNumbers 생성
        WinningStatistics statistics = new WinningStatistics(lottos, winningNumbers);
        OutputView.printWinningStatistics(statistics);
    }
}
