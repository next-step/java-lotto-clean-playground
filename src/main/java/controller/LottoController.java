package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private int price;

    public LottoController(InputView inputView, OutputView outputView, int price) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.price = price;
    }

    public void LottoRun() {
        price = inputView.inputPrice();
        LottoMarket lottoMarket = new LottoMarket(price);
        Lottos lottos = lottoMarket.generateTickets();
        outputView.printLottoTickets(lottos.getLottos().size());

        generateLotto(lottos);
        LottoWin(lottos);
    }

    private void generateLotto(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            outputView.printLotto(lotto.getLottoNumbers());
        }
    }

    private void LottoWin(Lottos lottos) {
        String inputWinNumber = inputView.inputWinningNumber();
        LottoWin lottoWinning = new LottoWin(lottos.getLottos(), inputWinNumber);
        LottoResult lottoResult = new LottoResult(lottoWinning.getWinCount(), price);

        int[] rankCounts = lottoResult.getRankCounts();
        List<int[]> Ranks = new ArrayList<>();
        createRank(lottoResult, Ranks);

        outputView.printWinningResult(rankCounts, Ranks);
        outputView.printProfit(lottoResult.getProfitRate());
    }

    private void createRank(LottoResult lottoResult, List<int[]> Ranks) {
        for (LottoRank rank : lottoResult.getRanks()) {
            Ranks.add(new int[]{rank.getCount(), rank.getPrize()});
        }
    }
}
