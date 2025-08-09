package controller;

import model.*;
import view.*;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final InputView input = new InputView();
    private final ResultView output = new ResultView();
    private final StaticsResult stats = new StaticsResult();

    public void run() {
        int amount = readPurchaseAmount();
        int manualCnt = readManualCount(amount);
        List<List<Integer>> manualNums = readManualNumbers(manualCnt);

        LottoTickets tickets = createTickets(amount, manualNums);
        printPurchasedLottos(tickets, manualCnt);

        WinningLotto winning = readWinningLotto();

        Map<Rank, Long> resultMap = calculateResults(tickets, winning);
        printStatisticsAndProfit(resultMap, amount);
    }

    private int readPurchaseAmount() {
        return input.inputLottoAmount();
    }

    private int readManualCount(int amount) {
        return input.manualLottoAmount(amount / LottoShop.PRICE_PER_TICKET);
    }

    private List<List<Integer>> readManualNumbers(int manualCnt) {
        return input.inputManualNumbers(manualCnt);
    }

    private LottoTickets createTickets(int amount, List<List<Integer>> manualNums) {
        LottoShop shop = new LottoShop(amount, manualNums);
        return shop.getTickets();
    }

    private void printPurchasedLottos(LottoTickets tickets, int manualCnt) {
        int autoCnt = tickets.getRandomLottos().size();
        output.printLottos(tickets.getAllLottos(), manualCnt, autoCnt);
    }

    private WinningLotto readWinningLotto() {
        return new WinningLotto(
                input.inputWinningNums(),
                input.inputBonusBall()
        );
    }

    private Map<Rank, Long> calculateResults(LottoTickets tickets, WinningLotto winning) {
        return stats.calculate(tickets.getAllLottos(), winning);
    }

    private void printStatisticsAndProfit(Map<Rank, Long> resultMap, int amount) {
        output.printWinningStatistics(resultMap);
        double rate = stats.profitRate(resultMap, amount);
        output.printProfitRate(rate);
    }
}
