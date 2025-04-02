import controller.LottoController;
import factory.LottoGeneratorFactory;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoGeneratorFactory generatorFactory = new LottoGeneratorFactory();

        LottoController controller = new LottoController(inputView, outputView, generatorFactory);

        controller.run();
    }
}
