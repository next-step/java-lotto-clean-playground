package utils;

import dto.MatchResultDto;
import dto.PrintableMatchDto;
import dto.PrintableProfitDto;
import dto.ProfitDto;

import java.util.List;

public class ResultFormatter {

    private static final String FORMAT_WITH_BONUS = "%d개 일치, 보너스 볼 일치(%d원)- %d개";
    private static final String FORMAT_BASIC = "%d개 일치 (%d원)- %d개";
    private static final String FORMAT_PROFIT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s이라는 의미임)";
    private static final String LOSS = "손해";
    private static final String GAIN = "이득";

    public PrintableMatchDto formatMatchResult(MatchResultDto match) {
        String line = formatMatchLine(match);
        return new PrintableMatchDto(line);
    }

    private String formatMatchLine(MatchResultDto match) {
        if (match.bonusMatch()) {
            return String.format(FORMAT_WITH_BONUS, match.matchCount(), match.prize(), match.count());
        }

        return String.format(FORMAT_BASIC, match.matchCount(), match.prize(), match.count());
    }

    public List<PrintableMatchDto> formatMatchResults(List<MatchResultDto> matches) {
        return matches.stream()
                .map(this::formatMatchResult)
                .toList();
    }

    public PrintableProfitDto formatProfitResult(ProfitDto profit) {
        String message = mapToProfitDescription(profit);
        String line = String.format(FORMAT_PROFIT, profit.rate(), message);
        return new PrintableProfitDto(line);
    }

    private String mapToProfitDescription(ProfitDto profit) {
        if (profit.isLoss()) {
            return LOSS;
        }

        return GAIN;
    }
}
