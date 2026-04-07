import controller.LottoController;
import view.ErrorView;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        ErrorView errorView = new ErrorView();

        LottoController lottoController = new LottoController(inputView, resultView, errorView);
        lottoController.run();
    }
}
