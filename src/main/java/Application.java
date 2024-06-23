import model.LottoGenerator;
import model.Lottos;
import model.LottoPurchaseMoney;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final LottoPurchaseMoney lottoPurchaseMoney = InputView.inputMoney();
        final LottoGenerator lottoGenerator = new LottoGenerator();
        final Lottos lottos = lottoGenerator.generateRandomLotto(lottoPurchaseMoney);
        OutputView.showLotto(lottos);
    }
}
