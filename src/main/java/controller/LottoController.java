package controller;

import domain.BuyAmount;
import domain.Lotto;
import domain.LottoMachine;
import domain.LottoTickets;
import domain.MatchResult;
import domain.WinningNumbers;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        int totalAmount = InputView.inputMoney();
        List<Lotto> handTickets = readManualTickets();
        BuyAmount buyAmount = new BuyAmount(totalAmount, handTickets.size());

        List<Lotto> lottos = buyLottoTickets(handTickets, buyAmount);
        OutputView.printTickets(lottos);

        WinningNumbers winningNumbers = createWinningNumbers();

        MatchResult matchResult = calculateMatchResult(lottos, winningNumbers);
        OutputView.printResult(matchResult);
        printProfit(matchResult, buyAmount);
    }

    private List<Lotto> readManualTickets() {
        int manualCount = InputView.inputManualTicketCount();
        List<String> manualInputs = InputView.writeManualTickets(manualCount);
        return manualInputs.stream()
            .map(Lotto::from)
            .collect(Collectors.toList());
    }

    private List<Lotto> buyLottoTickets(List<Lotto> manualTickets, BuyAmount buyAmount) {
        LottoMachine lottoMachine = new LottoMachine();
        int manualCount = buyAmount.getManualCount();
        int autoCount = buyAmount.getAutoCount();
        OutputView.printTicketCount(manualCount, autoCount);
        return lottoMachine.generateTickets(manualTickets, buyAmount.getAutoCount());
    }

    private WinningNumbers createWinningNumbers() {
        List<Integer> winningNumberInputs = InputView.inputWinningNumbers();
        int bonusNumberInput = InputView.inputBonusNumber();
        return new WinningNumbers(winningNumberInputs, bonusNumberInput);
    }

    private MatchResult calculateMatchResult(List<Lotto> tickets,
        WinningNumbers winningNumbers) {
        return new LottoTickets(tickets).countMatchResults(winningNumbers);
    }

    private void printProfit(MatchResult matchResult, BuyAmount buyAmount) {
        double profitRate = matchResult.calculateProfitRate(buyAmount);
        OutputView.printProfitRate(profitRate);
    }
}
