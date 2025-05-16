import controller.LottoController;
import service.generator.LottoGeneratorImpl;
import service.InputHandler;
import service.io.InputHandlerImpl;
import service.LottoGenerator;
import service.generator.LottoNumberGenerator;
import service.purchase.LottoPurchaseServiceImpl;
import service.generator.LottoTicketGenerator;
import service.OutputPresenter;
import service.io.OutputPresenterImpl;
import service.assembler.ResultViewModelAssembler;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoGenerator lottoGenerator = new LottoGeneratorImpl(
                new LottoNumberGenerator(),
                new LottoTicketGenerator()
        );

        LottoPurchaseServiceImpl purchaseService = new LottoPurchaseServiceImpl(lottoGenerator);
        ResultViewModelAssembler assembler = new ResultViewModelAssembler();

        InputHandler inputHandler = new InputHandlerImpl(inputView, outputView);
        OutputPresenter outputPresenter = new OutputPresenterImpl(outputView, assembler);

        LottoController controller = new LottoController(inputHandler, outputPresenter, purchaseService);
        controller.run();
    }
}
