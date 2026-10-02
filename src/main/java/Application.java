import domain.*;

import java.util.List;
import java.util.Scanner;

import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PurchaseAmount purchaseAmount = new PurchaseAmount(InputView.readPrice(sc));
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());

        List<Lotto> lottoList = lottoMachine.purchase(purchaseAmount.getLottosCount());
        Lottos lottos = new Lottos(lottoList);

        ResultView.printLottoResult(lottoList);

        Lotto winningLotto = new Lotto(InputView.readWinnerNumber(sc));
        int bonusNumber = InputView.readBonusNumber(sc);
        LottoResult result = lottos.calculateResult(winningLotto, bonusNumber);

        ResultView.printStats(result, purchaseAmount.getLottosCount());
    }
}
