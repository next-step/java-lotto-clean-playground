package domain.lotto.dto;

import java.util.List;

public class StatistsDto {

    private final List<MatchResult> matchResults;
    
    private final double profitRate;

    public StatistsDto(List<MatchResult> matchResults, double profitRate) {
        this.matchResults = matchResults;
        this.profitRate = profitRate;
    }

    public List<MatchResult> getMatchResults() {
        return matchResults;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
