package service.assembler;

import domain.LottoStatistics;
import domain.Profit;
import dto.PrintableMatchDto;
import dto.PrintableProfitDto;
import dto.WinningResultDto;
import dto.ProfitDto;
import utils.formatter.ResultFormatter;
import utils.mapper.ResultMapper;

import java.util.List;

public class ResultViewModelAssembler {

    private final ResultFormatter formatter = new ResultFormatter();

    public List<PrintableMatchDto> toPrintableMatchDtos(LottoStatistics statistics) {
        WinningResultDto resultDto = ResultMapper.toWinningResultDto(statistics);
        return formatter.formatMatchResults(resultDto.matches());
    }

    public PrintableProfitDto toPrintableProfitDto(Profit profit) {
        ProfitDto profitDto = ResultMapper.toProfitDto(profit);
        return formatter.formatProfitResult(profitDto);
    }
}
