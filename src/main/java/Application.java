import controller.LottoRunner;
import service.DefaultLottoGenerator;
import service.InputHandlerImpl;
import service.LottoGenerator;
import service.LottoNumberGenerator;
import service.LottoTicketGenerator;
import service.OutputPresenterImpl;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoNumberGenerator numberGenerator = new LottoNumberGenerator();
        LottoTicketGenerator ticketGenerator = new LottoTicketGenerator();
        LottoGenerator lottoGenerator = new DefaultLottoGenerator(numberGenerator, ticketGenerator);

        InputHandlerImpl inputHandler = new InputHandlerImpl(inputView, outputView);
        OutputPresenterImpl outputPresenter = new OutputPresenterImpl(outputView);

        LottoRunner runner = new LottoRunner(inputHandler, outputPresenter, lottoGenerator);
        runner.run();
    }
}
