package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoStatistics;
import domain.Lottos;
import domain.Profit;
import domain.WinningLotto;
import dto.LottoPurchaseDto;
import service.LottoPurchaseService;
import utils.BonusNumberParser;
import utils.LottoNumbersInputParser;
import utils.LottoPurchaseAmountParser;
import utils.ManualLottoCountParser;
import utils.WinningLottoParser;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService purchaseService;

    public LottoController(InputView inputView, OutputView outputView, LottoPurchaseService purchaseService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.purchaseService = purchaseService;
    }

    public void run() {
        LottoPurchaseDto request = createPurchaseRequest();
        Lottos purchasedLottos = purchaseService.purchase(request);

        outputView.printLottoPurchaseResultHeader(request.manualCount(), purchasedLottos.count() - request.manualCount());
        outputView.printLottoNumbers(purchasedLottos);

        outputView.printLastWeekWinningNumbersPrompt();
        WinningLotto winningLotto = WinningLottoParser.parse(inputView.readLastWeekWinningNumbers());

        outputView.printBonusNumberPrompt();
        LottoNumber bonusNumber = BonusNumberParser.parse(inputView.readBonusNumber(), winningLotto);

        LottoStatistics statistics = new LottoStatistics(purchasedLottos, winningLotto, bonusNumber);
        Profit profit = new Profit(statistics, request.purchaseAmount());

        outputView.printWinningStatistics(statistics.getRankStatistics());
        outputView.printProfit(outputView.toProfitMessage(profit));
    }

    private LottoPurchaseDto createPurchaseRequest() {
        outputView.printLottoPurchaseAmountPrompt();
        int purchaseAmount = LottoPurchaseAmountParser.parse(inputView.readLottoPurchaseAmount());

        outputView.printManualLottoCountPrompt();
        int manualCount = ManualLottoCountParser.parse(inputView.readManualLottoCount(), purchaseAmount);

        int autoCount = (purchaseAmount / Lotto.PRICE) - manualCount;

        outputView.printManualPurchaseLottoNumbersPrompt();
        List<String> inputs = inputView.readManualLottoNumbers(manualCount);

        List<Lotto> manualTickets = inputs.stream()
                .map(LottoNumbersInputParser::parse)
                .map(Lotto::new)
                .collect(Collectors.toList());

        Lottos manualLottos = new Lottos(manualTickets);

        return new LottoPurchaseDto(purchaseAmount, manualCount, manualLottos, autoCount);
    }
}
