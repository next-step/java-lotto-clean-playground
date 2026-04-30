package controller;

import domain.LottoNumber;
import domain.LottoService;
import domain.LottoTicket;
import domain.Statistic;
import domain.WinningNumbers;
import domain.WinningRank;
import java.util.Map;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;
    private final Statistic statistic;

    public Controller(InputView inputView, OutputView outputView,
                      LottoService lottoService, Statistic statistic) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
        this.statistic = statistic;
    }

    public void run(){
        int money = inputView.getMoney();
        int manualCount = inputView.getManualLottoCount();
        List<List<Integer>> manualTicketNumbers = inputView.getManualTicketNumbers(manualCount);

        List<LottoTicket> allTickets = lottoService.buyTickets(money, manualTicketNumbers);
        outputView.printLottoList(manualCount, allTickets.size(), allTickets);

        List<LottoNumber> winningNumberList = inputView.getWinningNumbers();
        LottoNumber bonusNumber = inputView.getBonusNumber();

        WinningNumbers winningNumbers = new WinningNumbers(winningNumberList, bonusNumber);

        Map<WinningRank, Integer> winningResult = statistic.getWinningResult(allTickets, winningNumbers);
        double revenue = statistic.getRevenue(money, winningResult);

        outputView.printResult(winningResult, revenue);
    }
}
