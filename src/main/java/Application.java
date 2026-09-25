import controller.LottoController;
import domain.*;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoFactory lottoFactory = new LottoFactory(numberGenerator);

        LottoController controller = new LottoController(
                inputView,
                outputView,
                lottoFactory
        );
        controller.run();
    }
}
