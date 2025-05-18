package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoStatistics;
import domain.Lottos;
import domain.Profit;
import domain.WinningLotto;
import dto.LottoPurchaseDto;
import service.LottoPurchaseService;
import service.ResultViewModelAssembler;
import utils.converter.LottoNumbersOutputConverter;
import utils.parser.BonusNumberParser;
import utils.parser.LottoNumbersInputParser;
import utils.parser.LottoPurchaseAmountParser;
import utils.parser.ManualLottoCountParser;
import utils.parser.WinningLottoParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService purchaseService;
    private final ResultViewModelAssembler assembler;

    public LottoController(InputView inputView, OutputView outputView,
                           LottoPurchaseService purchaseService, ResultViewModelAssembler assembler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.purchaseService = purchaseService;
        this.assembler = assembler;
    }

    public void run() {
        LottoPurchaseDto request = createPurchaseRequest();
        Lottos purchasedLottos = purchaseService.purchase(request);

        outputView.printLottoPurchaseResultHeader(request.manualCount(), purchasedLottos.count() - request.manualCount());
        outputView.printLottoNumbers(LottoNumbersOutputConverter.convert(purchasedLottos));
        System.out.println();

        outputView.printLastWeekWinningNumbersPrompt();
        WinningLotto winningLotto = WinningLottoParser.parse(inputView.readLastWeekWinningNumbers());

        outputView.printBonusNumberPrompt();
        LottoNumber bonusNumber = BonusNumberParser.parse(inputView.readBonusNumber(), winningLotto);

        LottoStatistics statistics = new LottoStatistics(purchasedLottos, winningLotto, bonusNumber);
        Profit profit = new Profit(statistics, request.purchaseAmount());

        outputView.printWinningStatistics(assembler.toPrintableMatchDtos(statistics));
        outputView.printProfit(assembler.toPrintableProfitDto(profit));
    }

    private LottoPurchaseDto createPurchaseRequest() {
        outputView.printLottoPurchasePrompt();
        int purchaseAmount = LottoPurchaseAmountParser.parse(inputView.readLottoPurchaseAmount());

        outputView.printManualLottoCountPrompt();
        int manualCount = ManualLottoCountParser.parse(inputView.readManualLottoCount(), purchaseAmount);

        outputView.printManualPurchaseLottoNumbersPrompt();
        List<String> inputs = inputView.readManualLottoNumbers(manualCount);

        List<Lotto> manualTickets = inputs.stream()
                .map(LottoNumbersInputParser::parse)
                .map(Lotto::new)
                .toList();

        return new LottoPurchaseDto(purchaseAmount, manualCount, new Lottos(manualTickets));
    }

}
