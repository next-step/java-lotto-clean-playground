package service.io;

import domain.Lottos;
import domain.LottoStatistics;
import domain.Profit;
import dto.LottoNumbersDto;
import dto.LottoPurchaseSummaryDto;
import dto.PrintableMatchDto;
import dto.PrintableProfitDto;
import service.OutputPresenter;
import service.assembler.ResultViewModelAssembler;
import view.OutputView;
import utils.converter.LottoNumbersOutputConverter;

import java.util.List;

public class OutputPresenterImpl implements OutputPresenter {

    private final OutputView outputView;
    private final ResultViewModelAssembler assembler;

    public OutputPresenterImpl(OutputView outputView, ResultViewModelAssembler assembler) {
        this.outputView = outputView;
        this.assembler = assembler;
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
        List<PrintableMatchDto> matchDtos = assembler.toPrintableMatchDtos(statistics);
        PrintableProfitDto profitDto = assembler.toPrintableProfitDto(profit);

        outputView.printWinningStatistics(matchDtos);
        outputView.printProfit(profitDto);
    }
}
