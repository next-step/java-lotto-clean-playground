import controller.LottoController;
import service.LottoGenerator;
import service.LottoNumberGenerator;
import service.LottoService;
import view.ConsoleInputView;
import view.ConsoleOutputView;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();

        LottoGenerator lottoGenerator = new LottoGenerator(new LottoNumberGenerator());
        LottoService purchaseService = new LottoService(lottoGenerator);

        LottoController controller = new LottoController(inputView, outputView, purchaseService);
        controller.run();
    }
}
