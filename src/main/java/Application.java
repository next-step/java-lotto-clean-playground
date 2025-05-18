import controller.LottoController;
import service.InputHandler;
import service.LottoGenerator;
import service.OutputPresenter;
import service.assembler.ResultViewModelAssembler;
import service.generator.LottoNumberGenerator;
import service.io.InputHandlerImpl;
import service.io.OutputPresenterImpl;
import service.LottoPurchaseService;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoGenerator lottoGenerator = new LottoGenerator(new LottoNumberGenerator());

        LottoPurchaseService purchaseService = new LottoPurchaseService(lottoGenerator);
        ResultViewModelAssembler assembler = new ResultViewModelAssembler();

        InputHandler inputHandler = new InputHandlerImpl(inputView, outputView);
        OutputPresenter outputPresenter = new OutputPresenterImpl(outputView, assembler);

        LottoController controller = new LottoController(inputHandler, outputPresenter, purchaseService);
        controller.run();
    }
}
