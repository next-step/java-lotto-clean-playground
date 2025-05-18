import domain.AutoLottoGenerator;
import domain.LottoGenerator;
import domain.LottoHistory;
import domain.LottoManager;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        int money = InputView.readBuyMoney();

        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager manager = new LottoManager(generator);
        LottoHistory history = manager.purchaseLottos(money);

        OutputView.printLottoHistory(history);

    }
}
