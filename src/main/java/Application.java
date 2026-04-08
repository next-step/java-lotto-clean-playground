import controller.Controller;
import domain.*;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoTicketGenerator lottoTicketGenerator = new RandomLottoTicketGenerator();
        Cashier cashier = new Cashier(lottoTicketGenerator);
        Controller controller = new Controller(inputView, outputView, cashier);
        controller.run();
    }
}
