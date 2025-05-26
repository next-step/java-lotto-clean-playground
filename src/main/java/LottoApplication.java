import domain.LottoMachine;
import domain.LottoNumbers;
import domain.LottoResult;
import domain.LottoTicket;
import domain.LottoTickets;
import domain.Money;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        LottoMachine lottoMachine = new LottoMachine();
        int ticketCount = money.getTicketCount();
        List<LottoTicket> tickets = lottoMachine.generateTickets(ticketCount);

        OutputView.printTicketCount(ticketCount);
        OutputView.printTickets(tickets);

        List<Integer> winningNumbersInput = InputView.inputWinningNumbers();
        LottoNumbers winningNumbers = new LottoNumbers(winningNumbersInput);

        LottoTickets lottoTickets = new LottoTickets(tickets);
        Map<Integer, Integer> matchResults = lottoTickets.countMatchResults(winningNumbers);

        OutputView.printResult(matchResults);

        int totalPrize = LottoResult.calculateTotalPrize(matchResults);
        double profitRate = LottoResult.calculateRateOfReturn(totalPrize, money.getAmount());
        OutputView.printProfitRate(profitRate);
    }
}
