package utils;

import dto.MatchResultDto;
import dto.PrintableMatchDto;
import dto.PrintableProfitDto;
import dto.ProfitDto;

import java.util.List;

public class ResultFormatter {

    private static final String MATCH_RESULT_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final String PROFIT_RESULT_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s이라는 의미임)";
    private static final String LOSS = "손해";
    private static final String GAIN = "이득";

    public PrintableMatchDto formatMatchResult(MatchResultDto match) {
        String line = String.format(
                MATCH_RESULT_FORMAT,
                match.matchCount(),
                match.prize(),
                match.count()
        );
        return new PrintableMatchDto(line);
    }

    public List<PrintableMatchDto> formatMatchResults(List<MatchResultDto> matches) {
        return matches.stream()
                .map(this::formatMatchResult)
                .toList();
    }

    public PrintableProfitDto formatProfitResult(ProfitDto profit) {
        String message = mapToProfitDescription(profit);
        String line = String.format(PROFIT_RESULT_FORMAT, profit.rate(), message);
        return new PrintableProfitDto(line);
    }

    private String mapToProfitDescription(ProfitDto profit) {
        if (profit.isLoss()) {
            return LOSS;
        }

        return GAIN;
    }
}
