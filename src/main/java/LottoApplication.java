import controller.LottoController;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoController controller = new LottoController(inputView, outputView);
        controller.run();
    }
}
