package controller;

import domain.LottoMarket;
import domain.Lottos;
import domain.Lotto;
import domain.LottoRank;
import domain.LottoWin;
import domain.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private int price;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
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
        int userBonusBall = inputView.inputBonusBall();
        Lotto lotto = Lotto.createLotto();
        int generateBonusNumber = lotto.getBonusNumber();

        LottoWin lottoWinning = new LottoWin();
        List<LottoRank> winCounts = lottoWinning.calculateWinCounts(lottos.getLottos(), inputWinNumber, userBonusBall, generateBonusNumber);
        LottoResult lottoResult = new LottoResult(winCounts, price);

        outputView.printWinningResult(lottoResult.getRankResult().getRankCounts());
        outputView.printProfit(lottoResult.getProfitRate());
    }
}
