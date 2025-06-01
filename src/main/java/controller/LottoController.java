package controller;

import domain.BonusNumber;
import domain.BuyAmount;
import domain.LottoMachine;
import domain.LottoTicket;
import domain.LottoTickets;
import domain.MatchResult;
import domain.Prize;
import domain.WinningNumbers;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class LottoController {
    //메서드의 길이가 10이 넘지 않도록 한다.
    public void run() {
        int totalAmount = InputView.inputMoney();
        List<LottoTicket> handTickets = readHandTickets();
        BuyAmount buyAmount = new BuyAmount(totalAmount, handTickets.size());

        List<LottoTicket> lottoTickets = buyLottoTickets(handTickets, buyAmount);
        OutputView.printTickets(lottoTickets);

        BonusNumber bonusNumber = new BonusNumber(InputView.inputBonusNumber());
        WinningNumbers winningNumbers = createWinningNumbers(bonusNumber);

        MatchResult matchResult = calculateMatchResult(lottoTickets, winningNumbers, bonusNumber);
        OutputView.printResult(matchResult);
        printProfit(matchResult, buyAmount);
    }

    private List<LottoTicket> readHandTickets() {
        int handCount = InputView.howManyTimeBuyHandTicket();
        List<String> handInputs = InputView.writeHandTickets(handCount);
        return handInputs.stream()
            .map(LottoTicket::from)
            .collect(Collectors.toList());
    }

    private List<LottoTicket> buyLottoTickets(List<LottoTicket> handTickets, BuyAmount buyAmount) {
        LottoMachine lottoMachine = new LottoMachine();
        int handCount = buyAmount.getHandCount();
        int autoCount = buyAmount.getAutoCount();
        OutputView.printTicketCount(handCount, autoCount);
        return lottoMachine.generateTickets(handTickets, buyAmount.getAutoCount());
    }

    private WinningNumbers createWinningNumbers(BonusNumber bonusNumber) {
        WinningNumbers winningNumbers = new WinningNumbers(InputView.inputWinningNumbers());
        winningNumbers.validateBonusNumber(bonusNumber);
        return winningNumbers;
    }

    private MatchResult calculateMatchResult(List<LottoTicket> tickets,
        WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        return new LottoTickets(tickets).countMatchResults(winningNumbers, bonusNumber);
    }

    private void printProfit(MatchResult matchResult, BuyAmount buyAmount) {
        double profitRate = Prize.calculateRateOfReturn(
            matchResult.calculateTotalPrize(),
            buyAmount.getAmount()
        );
        OutputView.printProfitRate(profitRate);
    }
}
