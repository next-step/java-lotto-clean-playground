import controller.LottoController;
import controller.Validator;
import util.LottoNumbersGenerator;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator();
        Validator validator = new Validator();
        LottoController lottoController = new LottoController(inputView, outputView, lottoNumbersGenerator, validator);

        lottoController.run();
    }
}
