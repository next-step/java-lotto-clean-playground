package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoNumber;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import domain.RandomLottoNumberGenerator;
import domain.WinningLotto;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public void run() {
        Money money = new Money(InputView.getPurchaseMoney());
        Lottos lottos = buy(money);
        WinningLotto winningLotto = readWinningLotto();
        LottoResult result = new LottoResult(lottos, winningLotto);
        ResultView.printStatistics(result, money);
    }

    private Lottos buy(Money money) {
        int manualCount = InputView.getManualCount();
        List<Lotto> manualLottos = toManualLottos(InputView.getManualLottoNumbers(manualCount));
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        Lottos lottos = lottoMachine.buy(money, manualLottos);
        ResultView.printLottos(lottos, manualLottos.size());
        return lottos;
    }

    private List<Lotto> toManualLottos(List<List<String>> rawLottos) {
        return rawLottos.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());
    }

    private WinningLotto readWinningLotto() {
        Lotto winningNumbers = Lotto.from(InputView.getWinningNumbers());
        LottoNumber bonus = LottoNumber.of(InputView.getBonusNumber());
        return new WinningLotto(winningNumbers, bonus);
    }
}
