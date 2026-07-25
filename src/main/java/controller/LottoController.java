package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoNumber;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import domain.RandomLottoNumberGenerator;
import domain.WinningLotto;
import domain.WinningNumbers;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public void run() {
        Money amount = new Money(InputView.getPurchaseAmount());
        Lottos lottos = buy(amount);
        WinningLotto winningLotto = readWinningLotto();
        LottoResult result = new LottoResult(lottos, winningLotto);
        ResultView.printStatistics(result, amount);
    }

    private Lottos buy(Money amount) {
        int manualCount = InputView.getManualCount();
        List<Lotto> manualLottos = toManualLottos(InputView.getManualLottoNumbers(manualCount));
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        Lottos lottos = lottoMachine.buy(amount, manualLottos);
        ResultView.printLottos(lottos, manualLottos.size());
        return lottos;
    }

    private List<Lotto> toManualLottos(List<List<String>> rawLottos) {
        return rawLottos.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());
    }

    private WinningLotto readWinningLotto() {
        WinningNumbers winningNumbers = WinningNumbers.from(InputView.getWinningNumbers());
        LottoNumber bonus = new LottoNumber(InputView.getBonusNumber());
        return new WinningLotto(winningNumbers, bonus);
    }
}
