import domain.Cashier;
import domain.Lotto;
import domain.NumberListGenerator;
import domain.RandomNumberListGenerator;
import view.InputView;
import view.OutputView;

public class Application {

    public static final int TICKET_LENGTH = 6;

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        NumberListGenerator numberListGenerator = new RandomNumberListGenerator(TICKET_LENGTH);
        Cashier cashier = new Cashier(numberListGenerator);

        int price = inputView.inputPrice();
        Lotto lotto = cashier.generateTickets(price);
        outputView.showNumberOfTickets(lotto.getNumberOfTickets());
        outputView.showLottoTickets(lotto);

    }
}
