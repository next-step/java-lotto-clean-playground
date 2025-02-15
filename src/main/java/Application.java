import java.util.List;

import domain.Lotto;
import domain.LottoMachine;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        int money = InputView.inputMoney();

        LottoMachine LottoMachine = new LottoMachine();
        List<Lotto> lottos = LottoMachine.buyLotto(money);

        ResultView.outputResult(lottos);
    }
}
