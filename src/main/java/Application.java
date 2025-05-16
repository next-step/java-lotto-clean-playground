import domain.LottoHistory;
import domain.LottoManager;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        int money = InputView.readBuyMoney();

        LottoManager manager = new LottoManager();
        LottoHistory history = manager.purchaseLottos(money);

        OutputView.printLottoHistory(history);

    }
}
