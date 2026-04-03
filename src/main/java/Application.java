import domain.Cashier;
import domain.Lotto;
import domain.LottoResult;
import domain.LottoTicket;
import domain.LottoTicketGenerator;
import domain.RandomLottoTicketGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoTicketGenerator lottoTicketGenerator = new RandomLottoTicketGenerator();
        Cashier cashier = new Cashier(lottoTicketGenerator);

        int price = inputView.inputPrice();
        Lotto lotto = cashier.generateTickets(price);
        outputView.showLottoTickets(lotto);

        LottoTicket winnerTicket = inputView.getWinnerTicket();
        LottoResult result = lotto.getResults(winnerTicket);
        outputView.showLottoResults(result, cashier.getProfitRate(result, price));
    }
}
