import model.LottoGenerator;
import model.Lottos;
import model.Money;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final Money money = InputView.inputMoney();
        final LottoGenerator lottoGenerator = new LottoGenerator();
        final Lottos lottos = lottoGenerator.generateRandomLotto(money);
        OutputView.showLotto(lottos);
    }
}
