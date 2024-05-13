import controller.BuyLottoControlller;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();
        BuyLottoControlller buyLottoControlller = new BuyLottoControlller(inputView,outputView);
        buyLottoControlller.buyLotto();
    }
}
