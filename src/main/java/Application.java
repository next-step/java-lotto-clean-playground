import domain.Cashier;
import domain.Lotto;
import domain.LottoResult;
import domain.LottoTicket;
import domain.NumberListGenerator;
import domain.RandomNumberListGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static final Integer TICKET_LENGTH = 6;

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        NumberListGenerator numberListGenerator = new RandomNumberListGenerator(TICKET_LENGTH);
        Cashier cashier = new Cashier(numberListGenerator);

        Integer price = inputView.inputPrice();
        Lotto lotto = cashier.generateTickets(price);
        outputView.showLottoTickets(lotto);

        LottoTicket winnerTicket = inputView.getWinnerTicket();
        LottoResult result = cashier.getResults(lotto, winnerTicket);
        outputView.showLottoResults(result, cashier.getProfitRate(result, price));
    }
}
