import controller.Controller;
import domain.LottoTicketGenerator;
import domain.RandomLottoTicketGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoTicketGenerator lottoTicketGenerator = new RandomLottoTicketGenerator();
        Controller controller = new Controller(inputView, outputView, lottoTicketGenerator);
        controller.run();
    }
}
