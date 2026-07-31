import domain.Lottos;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        int amount = inputView.readPurchaseAmount();

        Lottos lottos = new Lottos(amount);

        outputView.printPurchasedCount(lottos);
        outputView.printLottos(lottos);
    }
}
