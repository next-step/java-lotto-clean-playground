import domain.*;
import dto.LottoNumbersDto;
import dto.ProfitDto;
import dto.WinningResultDto;
import utils.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoRunner {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoRunner(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        int purchaseAmount = requestPurchaseAmount();
        Lottos purchasedLottos = generateLottos(purchaseAmount);
        printPurchasedLottos(purchasedLottos);

        WinningNumbers winningNumbers = requestWinningNumbers();
        LottoStatistics statistics = new LottoStatistics(purchasedLottos, winningNumbers);
        Profit profit = new Profit(statistics, purchaseAmount);

        printResult(statistics, profit);
    }

    private int requestPurchaseAmount() {
        outputView.printLottoPurchasePrompt();
        String amountInput = inputView.readLottoPurchaseAmount();
        return LottoPurchaseAmountParser.parse(amountInput);
    }

    private Lottos generateLottos(int purchaseAmount) {
        return lottoGenerator.generate(purchaseAmount);
    }

    private void printPurchasedLottos(Lottos lottos) {
        System.out.println();
        outputView.printLottoPurchaseResultHeader(lottos.count());
        List<LottoNumbersDto> lottoNumberDtos = LottoNumbersParser.parse(lottos);
        outputView.printLottoNumbers(lottoNumberDtos);
        System.out.println();
    }

    private WinningNumbers requestWinningNumbers() {
        outputView.printLastWeekWinningNumbersPrompt();
        String winningInput = inputView.readLastWeekWinningNumbers();
        System.out.println();
        return WinningNumbersParser.parse(winningInput);
    }

    private void printResult(LottoStatistics statistics, Profit profit) {
        ResultFormatter formatter = new ResultFormatter();

        WinningResultDto winningResultDto = ResultMapper.toWinningResultDto(statistics);
        ProfitDto profitDto = ResultMapper.toProfitDto(profit);

        outputView.printWinningStatistics(formatter.formatMatchResults(winningResultDto.matches()));
        outputView.printProfit(formatter.formatProfitResult(profitDto));
    }
}

