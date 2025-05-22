package controller;

import domain.Lotto;
import domain.LottoStatistics;
import domain.Profit;
import domain.WinningLotto;
import dto.LottoPurchaseDto;
import service.LottoService;
import utils.WinningLottoParser;
import view.InputView;
import view.OutputView;

import java.util.List;

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

        List<Lotto> purchasedLottos = lottoService.generateLottos(purchaseRequest);

        outputView.printLottoPurchaseResultHeader(
                purchaseRequest.manualLottoCount(),
                purchasedLottos.size() - purchaseRequest.manualLottoCount()
        );
        outputView.printLottoNumbers(purchasedLottos);

        outputView.printLastWeekWinningNumbersPrompt();
        String winningNumbersInput = inputView.readLastWeekWinningNumbers();

        outputView.printBonusNumberPrompt();
        String bonusNumberInput = inputView.readBonusNumber();

        WinningLotto winningLotto = WinningLottoParser.parse(winningNumbersInput, bonusNumberInput);

        LottoStatistics statistics = new LottoStatistics(purchasedLottos, winningLotto);
        Profit profit = new Profit(statistics, purchaseRequest.totalAmount());

        outputView.printWinningStatistics(statistics.getRankStatistics());
        outputView.printProfit(outputView.toProfitMessage(profit));
    }
}
