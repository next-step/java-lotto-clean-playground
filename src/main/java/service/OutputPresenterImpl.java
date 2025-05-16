package service;

import domain.*;
import dto.*;
import utils.*;
import view.OutputView;

import java.util.List;

public class OutputPresenterImpl implements OutputPresenter {

    private final OutputView outputView;

    public OutputPresenterImpl(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public void showPurchasedLottos(int manualCount, Lottos lottos) {
        int autoCount = lottos.count() - manualCount;
        LottoPurchaseSummaryDto summaryDto = new LottoPurchaseSummaryDto(manualCount, autoCount);

        System.out.println();
        outputView.printLottoPurchaseResultHeader(summaryDto.manualCount(), summaryDto.autoCount());
        List<LottoNumbersDto> dtoList = LottoNumbersOutputConverter.convert(lottos);
        outputView.printLottoNumbers(dtoList);
        System.out.println();
    }

    @Override
    public void showStatistics(LottoStatistics statistics, Profit profit) {
        ResultFormatter formatter = new ResultFormatter();
        WinningResultDto resultDto = ResultMapper.toWinningResultDto(statistics);
        ProfitDto profitDto = ResultMapper.toProfitDto(profit);

        outputView.printWinningStatistics(formatter.formatMatchResults(resultDto.matches()));
        outputView.printProfit(formatter.formatProfitResult(profitDto));
    }
}
