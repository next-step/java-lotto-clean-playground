import controller.LottoController;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();
        LottoController buyLottoController = new LottoController(inputView, outputView);
        buyLottoController.startLotto();
    }
}
