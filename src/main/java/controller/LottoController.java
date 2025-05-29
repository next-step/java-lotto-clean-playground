package controller;

import domain.BuyAmount;
import domain.LottoMachine;
import domain.LottoResult;
import domain.LottoTicket;
import domain.LottoTickets;
import domain.MatchResult;
import domain.WinningNumbers;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        //로또들을 구매하고 티켓을 생성한다
        BuyAmount buyAmount = new BuyAmount(InputView.inputMoney());
        List<LottoTicket> lottoTickets = buyLottoTickets(buyAmount);

        //구매한 로또들을 출력한다
        OutputView.printTickets(lottoTickets);

        //저번 주 당첨번호를 입력한다
        WinningNumbers winningNumbers = new WinningNumbers(InputView.inputWinningNumbers());

        //등수에 따라 몇 개가 맞았는지 출력한다
        MatchResult matchResult = calculateMatchResult(lottoTickets, winningNumbers);
        OutputView.printResult(matchResult);

        //수익률을 계산하고 출력한다
        printProfit(matchResult, buyAmount);
    }

    private List<LottoTicket> buyLottoTickets(BuyAmount buyAmount) {
        LottoMachine lottoMachine = new LottoMachine();
        int ticketCount = buyAmount.getPurchasableTicketCount();
        OutputView.printTicketCount(ticketCount);
        return lottoMachine.generateTickets(ticketCount);
    }

    private MatchResult calculateMatchResult(List<LottoTicket> tickets, WinningNumbers winningNumbers) {
        return new LottoTickets(tickets).countMatchResults(winningNumbers);
    }

    private void printProfit(MatchResult matchResult, BuyAmount buyAmount) {
        double profitRate = LottoResult.calculateRateOfReturn(
            matchResult.calculateTotalPrize(),
            buyAmount.getAmount()
        );
        OutputView.printProfitRate(profitRate);
    }
}
