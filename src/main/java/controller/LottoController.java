package controller;

import domain.LottoNumber;
import domain.LottoStatistics;
import domain.Lottos;
import domain.Profit;
import domain.WinningLotto;
import dto.LottoPurchaseDto;
import service.LottoService;
import utils.BonusNumberParser;
import utils.WinningLottoParser;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        LottoPurchaseDto purchaseRequest = lottoService.preparePurchase(inputView, outputView);

        Lottos purchasedLottos = lottoService.generateLottos(purchaseRequest);

        outputView.printLottoPurchaseResultHeader(purchaseRequest.manualLottoCount(), purchasedLottos.count() - purchaseRequest.manualLottoCount());
        outputView.printLottoNumbers(purchasedLottos);

        outputView.printLastWeekWinningNumbersPrompt();
        WinningLotto winningLotto = WinningLottoParser.parse(inputView.readLastWeekWinningNumbers());

        outputView.printBonusNumberPrompt();
        LottoNumber bonusNumber = BonusNumberParser.parse(inputView.readBonusNumber(), winningLotto);

        LottoStatistics statistics = new LottoStatistics(purchasedLottos, winningLotto, bonusNumber);

        Profit profit = new Profit(statistics, purchaseRequest.totalAmount());

        outputView.printWinningStatistics(statistics.getRankStatistics());
        outputView.printProfit(outputView.toProfitMessage(profit));
    }
}
