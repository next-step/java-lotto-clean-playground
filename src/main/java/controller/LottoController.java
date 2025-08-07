package controller;

import model.*;
import view.*;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final InputView in = new InputView();
    private final ResultView out = new ResultView();
    private final StaticsResult stats = new StaticsResult();

    public void run() {
        int amount = in.inputLottoAmount();
        int manualCnt = in.manualLottoAmount(amount / 1000);
        List<List<Integer>> manualNums = in.inputManualNumbers(manualCnt);

        LottoShop shop = new LottoShop(amount, manualNums);
        LottoTickets tickets = shop.getTickets();
        out.printLottos(tickets.getAllLottos(), manualCnt, tickets.getRandomLottos().size());

        WinningLotto winning = new WinningLotto(
                in.inputWinningNums(), in.inputBonusBall()
        );

        Map<Rank, Long> resultMap = stats.calculate(tickets.getAllLottos(), winning);
        out.printWinningStatistics(resultMap);

        double rate = stats.profitRate(resultMap, amount);
        out.printProfitRate(rate);
    }
}
