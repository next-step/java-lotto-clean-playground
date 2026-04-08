import domain.*;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoTicketGenerator lottoTicketGenerator = new RandomLottoTicketGenerator();
        Cashier cashier = new Cashier(lottoTicketGenerator);

        Price price = inputView.inputPrice();
        Lotto lotto = cashier.generateTickets(price);
        outputView.showLottoTickets(lotto);

        LottoResult result = lotto.getResults(inputView.getWinnerTicket(), inputView.getBonusNumber());
        outputView.showLottoResults(result, cashier.getProfitRate(result, price));
    }
}
