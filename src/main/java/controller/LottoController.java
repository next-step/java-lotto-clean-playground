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
        int amount = input.inputLottoAmount();
        int manualCnt = input.manualLottoAmount(amount / 1000);
        List<List<Integer>> manualNums = input.inputManualNumbers(manualCnt);

        LottoShop shop = new LottoShop(amount, manualNums);
        LottoTickets tickets = shop.getTickets();
        output.printLottos(tickets.getAllLottos(), manualCnt, tickets.getRandomLottos().size());

        WinningLotto winning = new WinningLotto(
                input.inputWinningNums(), input.inputBonusBall()
        );

        Map<Rank, Long> resultMap = stats.calculate(tickets.getAllLottos(), winning);
        output.printWinningStatistics(resultMap);

        double rate = stats.profitRate(resultMap, amount);
        output.printProfitRate(rate);
    }
}
