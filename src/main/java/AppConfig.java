import controller.LottoController;
import controller.Validator;
import util.LottoNumbersGenerator;
import view.InputView;
import view.OutputView;

public class AppConfig {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator();
    private final Validator validator = new Validator();

    public LottoController setLottoController() {
        return new LottoController(inputView, outputView, lottoNumbersGenerator, validator);
    }
}
